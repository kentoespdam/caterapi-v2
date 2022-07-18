package com.kentoes.caterapi.controllers.kondisi;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.kondisi.Kondisi;
import com.kentoes.caterapi.models.services.kondisi.KondisiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/kondisis")
public class KondisiController implements IKondisiController, BaseController {
    @Autowired
    private KondisiService service;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req) {
        return pageResult.build(httpServletRequest, service.findAll(req), req);
    }

    @Override
    public ResponseEntity<?> findById(FindByIdReq req) {
        return singleResult.build(service.findById(req.getLongId()));
    }

    @Override
    public ResponseEntity<?> findByKode(FindByIdReq req) {
        return singleResult.build(service.findByKode(req.getId()));
    }

    @Override
    public ResponseEntity<?> save(KondisiSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsByKode(req.getKode()))
            return errorResult.build(
                    "kondisi with kode: " + req.getKode() + " is exists!",
                    HttpStatus.CONFLICT);
        Kondisi kondisi = modelMapper.map(req, Kondisi.class);
        Kondisi kondisiSave = service.save(kondisi);
        return saveResult.build(kondisiSave.getId());
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq reqId, KondisiSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!reqId.getLongId().equals(req.getId()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsByKode(req.getKode()))
            return errorResult.build(
                    "kondisi with kode: " + req.getKode() + " is not exists!",
                    HttpStatus.CONFLICT);
        Kondisi kondisi = modelMapper.map(req, Kondisi.class);
        Kondisi kondisiSave = service.save(kondisi);
        return saveResult.build(kondisiSave.getId());
    }

    @Override
    public ResponseEntity<?> saveAll(List<KondisiSaveReq> reqs, Errors errors) {
        List<Kondisi> kondisis = new ArrayList<>();
        for (KondisiSaveReq req : reqs) {
            if (service.existsByKode(req.getKode()))
                return errorResult.build(
                        "kondisi with kode: " + req.getKode() + " is exists!",
                        HttpStatus.CONFLICT);
            Kondisi kondisi = modelMapper.map(req, Kondisi.class);
            kondisis.add(kondisi);
        }
        service.saveAll(kondisis);
        return saveResult.build("batch");
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq req) {
        if (Objects.isNull(req))
            return errorResult.build("invalid kondisi id!", HttpStatus.BAD_REQUEST);
        if (!service.existsById(req.getLongId()))
            return errorResult.build("kondisi is not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        service.delete(req.getLongId());
        return singleResult.build("data deleted successfully");
    }
}
