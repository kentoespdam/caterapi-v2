package com.kentoes.caterapi.controllers.kondisi;

import com.kentoes.caterapi.controllers.IBaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

public interface IKondisiController extends IBaseController {
    @Override
    ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req);

    @Override
    ResponseEntity<?> findById(FindByIdReq req);

    @GetMapping("/{id}/kode")
    ResponseEntity<?> findByKode(FindByIdReq req);

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody KondisiSaveReq req, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid FindByIdReq reqId, @RequestBody KondisiSaveReq req, Errors errors);

    @PostMapping("/batch")
    ResponseEntity<?> saveAll(@Valid @RequestBody List<KondisiSaveReq> reqs, Errors errors);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(FindByIdReq req);
}
