package com.kentoes.caterapi.controllers;

import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public interface IBaseController {
    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req);

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(FindByIdReq req);
}
