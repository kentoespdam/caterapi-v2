package com.kentoes.caterapi.controllers.user;

import com.kentoes.caterapi.controllers.IBaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

public interface IUserController extends IBaseController {
    @Override
    ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req);

    @Override
    ResponseEntity<?> findById(FindByIdReq req);

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody UserSaveReq req, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid FindByIdReq reqId, @RequestBody UserSaveReq req, Errors errors);

    @PutMapping("/{id}/update_password")
    ResponseEntity<?> updatePassword(@Valid FindByIdReq reqId, @RequestBody UserPassUpdateReq req, Errors errors);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@Valid FindByIdReq reqs, Errors errors);

    @PostMapping("/batch")
    ResponseEntity<?> saveAll(@Valid @RequestBody List<UserSaveReq> req, Errors errors);
}
