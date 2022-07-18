package com.kentoes.caterapi.controllers.role;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/roles")
public class RoleController implements IRoleController, BaseController {
    @Autowired
    private RoleService service;

    @Override
    public ResponseEntity<?> findAll(HttpServletRequest request, PaggingReq req) {
        return pageResult.build(request, service.findAll(req), req);
    }

    @Override
    public ResponseEntity<?> findById(FindByIdReq req) {
        return singleResult.build(service.findByRoleName(req.getId()));
    }
}
