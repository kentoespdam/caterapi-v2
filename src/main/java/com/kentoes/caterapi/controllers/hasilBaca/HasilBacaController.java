package com.kentoes.caterapi.controllers.hasilBaca;

import com.kentoes.caterapi.config.FileUploadUtil;
import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLog;
import com.kentoes.caterapi.models.entities.customer.Customer;
import com.kentoes.caterapi.models.entities.enums.EConfirm;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBaca;
import com.kentoes.caterapi.models.entities.kondisi.Kondisi;
import com.kentoes.caterapi.models.services.confirmLog.ConfirmLogService;
import com.kentoes.caterapi.models.services.customer.CustomerService;
import com.kentoes.caterapi.models.services.hasilBaca.HasilBacaService;
import com.kentoes.caterapi.models.services.kondisi.KondisiService;
import com.kentoes.caterapi.models.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/hasil_bacas")
public class HasilBacaController implements IHasilBacaController, BaseController {
    @Autowired
    private HasilBacaService service;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private KondisiService kondisiService;
    @Autowired
    private ConfirmLogService confirmLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findUncheck(HttpServletRequest httpServletRequest, PaggingReq req) {
        return pageResult.build(httpServletRequest, service.findAllUncheck(req, 202207, ERole.ROLE_CATER), req);
    }

    @Override
    public ResponseEntity<?> save(HttpServletRequest request, HasilSaveReq req, Errors errors) {
        String username = "ADIPRIYO";
        if (errors.hasErrors())
            return errorResult.build(errors);
        if (service.existsByNosamwAndPeriode(req.getNosamw(), req.getPeriode()))
            return errorResult.build(
                    "Hasil baca with nosamw:" + req.getNosamw() + " and periode:" + req.getPeriode() + " is exist",
                    HttpStatus.CONFLICT);
        Customer customer = customerService.findByNosamw(req.getNosamw());
        if (customer == null)
            return errorResult.build(
                    "customer with nosamw:" + req.getNosamw() + " is not exist!",
                    HttpStatus.FORBIDDEN);
        Kondisi kondisi = kondisiService.findByKode(req.getKondisiKode());
        if (kondisi == null)
            return errorResult.build(
                    "kondisi is not exist!",
                    HttpStatus.FORBIDDEN);
        HasilBaca hasilBaca = modelMapper.map(req, HasilBaca.class);
        hasilBaca.setCheckDate(LocalDate.parse(req.getTgl()));
        hasilBaca.setCustomer(customer);
        hasilBaca.setKondisi(kondisi);
        hasilBaca.setPosisi(ERole.ROLE_CATER);

        String filePrefix = username + "_" + hasilBaca.getCustomer().getNosamw() + "_";
        String fileName = filePrefix + StringUtils.cleanPath(req.getImg().getOriginalFilename());
        hasilBaca.setFoto(fileName);
        if (!FileUploadUtil.saveFile(hasilBaca.getPeriode(), hasilBaca.getFoto(), req.getImg()))
            return errorResult.build(
                    "failed uploaded file!",
                    HttpStatus.INTERNAL_SERVER_ERROR);

        HasilBaca hasilBacaSave = service.save(hasilBaca);

        ConfirmLog confirmLog = new ConfirmLog(
                0L,
                hasilBacaSave,
                userService.findById(username),
                ERole.ROLE_CATER,
                EConfirm.OK,
                "");
        confirmLogService.save(confirmLog);

        return saveResult.build("{" +
                "id:" + hasilBacaSave.getId() + ", " +
                "nosamw:/\"" + hasilBacaSave.getCustomer().getNosamw() + "/\"," +
                "periode:" + hasilBacaSave.getPeriode() +
                "}");
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq reqId, HasilBacaUpdateReq req, Errors errors) {
        String username = "ADIPRIYO";
        if (errors.hasErrors())
            return errorResult.build(errors);
        if (Objects.isNull(reqId.getId()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!reqId.getLongId().equals(req.getId()))
            return errorResult.build("Invalid data request pair", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsById(reqId.getLongId()))
            return errorResult.build(
                    "Zone with id: " + reqId.getId() + " is not exists!",
                    HttpStatus.CONFLICT);
        HasilBaca hasilBaca = service.findById(req.getId());
        hasilBaca.setCheckDate(LocalDate.parse(req.getTgl()));
        hasilBaca.setPeriode(req.getPeriode());
        if (Objects.nonNull(req.getFoto())) {
            String oldName = hasilBaca.getFoto();
            String tmpName = "tmp_" + oldName;
            String filePrefix = username + "_" + hasilBaca.getCustomer().getNosamw() + "_";
            String fileName = filePrefix + StringUtils.cleanPath(req.getFoto().getOriginalFilename());
            FileUploadUtil.rename(hasilBaca.getPeriode(), oldName, tmpName);
            if (!FileUploadUtil.saveFile(req.getPeriode(), fileName, req.getFoto())) {
                FileUploadUtil.rename(hasilBaca.getPeriode(), tmpName, oldName);
                return errorResult.build(
                        "failed uploaded file!",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            FileUploadUtil.deleteFile(hasilBaca.getPeriode(), tmpName);
        }
        if (Objects.nonNull(req.getMetL())) hasilBaca.setMetL(req.getMetL());
        if (Objects.nonNull(req.getMetK())) hasilBaca.setMetK(req.getMetK());
        if (Objects.nonNull(req.getPakai())) hasilBaca.setPakai(req.getPakai());
        if (Objects.nonNull(req.getRata2())) hasilBaca.setRata2(req.getRata2());
        if (Objects.nonNull(req.getPosisi())) hasilBaca.setPosisi(req.getPosisi());
        if (Objects.nonNull(req.getKondisiKode()))
            hasilBaca.setKondisi(kondisiService.findByKode(req.getKondisiKode()));

        HasilBaca hasilBacaSave = service.save(hasilBaca);

        return saveResult.build("{" +
                "id:" + hasilBacaSave.getId() + ", " +
                "nosamw:/\"" + hasilBacaSave.getCustomer().getNosamw() + "/\"," +
                "periode:" + hasilBacaSave.getPeriode() +
                "}");
    }

    @Override
    public ResponseEntity<?> saveAll(List<HasilBaca> hasilBacas, Errors errors) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq reqId) {
        return null;
    }
}
