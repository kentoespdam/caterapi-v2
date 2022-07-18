package com.kentoes.caterapi.controllers.munit;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.entities.munit.MUnit;
import com.kentoes.caterapi.models.services.cabang.CabangService;
import com.kentoes.caterapi.models.services.munit.MUnitService;
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

@RestController
@RequestMapping("/api/units")
public class MunitController implements IMunitController, BaseController {
    @Autowired
    private MUnitService service;
    @Autowired
    private CabangService cabangService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req) {
        return pageResult.build(httpServletRequest, service.findAll(req), req);
    }

    @Override
    public ResponseEntity<?> findById(FindByIdReq req) {
        return singleResult.build(service.findById(req.getId()));
    }


    @Override
    public ResponseEntity<?> create(MUnitReq mUnitReq, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsByUnit(mUnitReq.getUnit()) || service.existsByName(mUnitReq.getName()))
            return errorResult.build("Unit is exists!", HttpStatus.CONFLICT);
        Cabang cabang = cabangService.findById(mUnitReq.getSatker());
        if (cabang == null) return errorResult.build("invalid satker code!");
        MUnit mUnit = modelMapper.map(mUnitReq, MUnit.class);
        mUnit.setCabang(cabangService.findById(mUnitReq.getSatker()));
        MUnit mUnitSave = service.save(mUnit);
        return saveResult.build(mUnitSave.getUnit());
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq reqId, MUnitReq mUnitReq, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!reqId.getId().equals(mUnitReq.getUnit()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsByUnit(mUnitReq.getUnit()))
            return errorResult.build("Unit is not exists!", HttpStatus.FORBIDDEN);
        Cabang cabang = cabangService.findById(mUnitReq.getSatker());
        if (cabang == null) return errorResult.build("invalid satker code!");
        MUnit mUnit = modelMapper.map(mUnitReq, MUnit.class);
        mUnit.setCabang(cabangService.findById(mUnitReq.getSatker()));
        MUnit mUnitSave = service.save(mUnit);
        return saveResult.build(mUnitSave.getUnit());
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq findByIdReq, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!service.existsByUnit(findByIdReq.getId()))
            return errorResult.build("Unit is not exist!", HttpStatus.FORBIDDEN);
        service.delete(findByIdReq.getId());
        return singleResult.build("data deleted successfully");
    }

    @Override
    public ResponseEntity<?> saveBatch(List<MUnitReq> mUnitReqs, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        List<MUnit> mUnits = new ArrayList<>();
        for (MUnitReq mUnitReq : mUnitReqs) {
            if (service.existsByUnit(mUnitReq.getUnit()) || service.existsByName(mUnitReq.getName()))
                return errorResult.build("Unit is exists with id: " + mUnitReq.getUnit(), HttpStatus.CONFLICT);

            Cabang cabang = cabangService.findById(mUnitReq.getSatker());
            if (cabang == null)
                return errorResult.build("Cabang not exist on unit with id: " + mUnitReq.getUnit());

            MUnit mUnit = modelMapper.map(mUnitReq, MUnit.class);
            mUnit.setCabang(cabangService.findById(mUnitReq.getSatker()));
            mUnits.add(mUnit);
        }
        service.saveAll(mUnits);
        return saveResult.build("batch");
    }
}
