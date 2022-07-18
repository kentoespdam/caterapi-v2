package com.kentoes.caterapi.controllers.munit;

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

public interface IMunitController extends IBaseController {
    @Override
    ResponseEntity<?> findAll(HttpServletRequest request, PaggingReq page);

    @Override
    ResponseEntity<?> findById(FindByIdReq req);

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody MUnitReq mUnitReq, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid FindByIdReq reqId, @RequestBody MUnitReq mUnitReq, Errors errors);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@Valid FindByIdReq findByIdReq, Errors errors);

    @PostMapping("/batch")
    ResponseEntity<?> saveBatch(@Valid @RequestBody List<MUnitReq> mUnitReqs, Errors errors);
}
