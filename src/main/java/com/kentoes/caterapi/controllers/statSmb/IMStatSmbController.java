package com.kentoes.caterapi.controllers.statSmb;

import com.kentoes.caterapi.controllers.IBaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

public interface IMStatSmbController extends IBaseController {
    @Override
    ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req);

    @Override
    ResponseEntity<?> findById(FindByIdReq req);

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody MStatSmbSaveReq req, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid FindByIdReq idReq, @RequestBody MStatSmbSaveReq req, Errors errors);

    @PostMapping("/batch")
    ResponseEntity<?> saveAll(@Valid @RequestBody List<MStatSmbSaveReq> reqs, Errors errors);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(FindByIdReq req);
}
