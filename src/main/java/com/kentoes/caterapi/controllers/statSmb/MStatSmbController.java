package com.kentoes.caterapi.controllers.statSmb;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmb;
import com.kentoes.caterapi.models.services.statSmb.MStatSmbService;
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
@RequestMapping("/api/stat_smb")
public class MStatSmbController implements IMStatSmbController, BaseController {
    @Autowired
    private MStatSmbService service;
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
    public ResponseEntity<?> save(MStatSmbSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsById(req.getStatSmb()))
            return errorResult.build("stat smb with id: " + req.getStatSmb() + " is exist!", HttpStatus.CONFLICT);
        MStatSmb mStatSmb = modelMapper.map(req, MStatSmb.class);
        MStatSmb mStatSmbSave = service.save(mStatSmb);
        return saveResult.build(mStatSmbSave.getStatSmb());
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq idReq, MStatSmbSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!idReq.getId().equals(req.getStatSmb()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsById(req.getStatSmb()))
            return errorResult.build("stat smb with id: " + idReq.getId() + " is not exist!", HttpStatus.CONFLICT);
        MStatSmb mStatSmb = modelMapper.map(req, MStatSmb.class);
        MStatSmb mStatSmbSave = service.save(mStatSmb);
        return saveResult.build(mStatSmbSave.getStatSmb());
    }

    @Override
    public ResponseEntity<?> saveAll(List<MStatSmbSaveReq> reqs, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        List<MStatSmb> mStatSmbs = new ArrayList<>();
        for (MStatSmbSaveReq req : reqs) {
            if (service.existsById(req.getStatSmb()))
                return errorResult.build("stat smb with id: " + req.getStatSmb() + " is exist!", HttpStatus.CONFLICT);
            MStatSmb mStatSmb = modelMapper.map(req, MStatSmb.class);
            mStatSmbs.add(mStatSmb);
        }
        service.saveAll(mStatSmbs);
        return saveResult.build("batch");
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq req) {
        if (Objects.isNull(req))
            return errorResult.build("invalid stat smb id!", HttpStatus.BAD_REQUEST);
        if (!service.existsById(req.getId()))
            return errorResult.build("stat smb is not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        service.delete(req.getId());
        return singleResult.build("data deleted successfully");
    }
}
