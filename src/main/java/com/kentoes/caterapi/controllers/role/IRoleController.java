package com.kentoes.caterapi.controllers.role;

import com.kentoes.caterapi.controllers.IBaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IRoleController extends IBaseController {
    @Override
    ResponseEntity<?> findAll(HttpServletRequest request, PaggingReq req);

    @Override
    ResponseEntity<?> findById(FindByIdReq req);
}
