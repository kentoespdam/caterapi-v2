package com.kentoes.caterapi.controllers.hasilBaca;

import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBaca;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

public interface IHasilBacaController {
    @GetMapping
    ResponseEntity<?> findUncheck(HttpServletRequest httpServletRequest, PaggingReq req);

    @PostMapping
    ResponseEntity<?> save(@Valid HttpServletRequest request, @ModelAttribute HasilSaveReq req, Errors errors);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@Valid FindByIdReq reqId, @ModelAttribute HasilBacaUpdateReq req, Errors errors);

    @PostMapping("/batch")
    ResponseEntity<?> saveAll(@Valid List<HasilBaca> hasilBacas, Errors errors);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(FindByIdReq reqId);
}
