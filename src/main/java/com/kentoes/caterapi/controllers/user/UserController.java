package com.kentoes.caterapi.controllers.user;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.entities.role.Role;
import com.kentoes.caterapi.models.entities.user.User;
import com.kentoes.caterapi.models.services.cabang.CabangService;
import com.kentoes.caterapi.models.services.role.RoleService;
import com.kentoes.caterapi.models.services.user.UserService;
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
@RequestMapping("/api/users")
public class UserController implements IUserController, BaseController {
    @Autowired
    private UserService service;
    @Autowired
    private CabangService cabangService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req) {
        return pageResult.build(httpServletRequest, service.findAll(req), req);
    }

    @Override
    public ResponseEntity<?> findById(FindByIdReq req) {
        return singleResult.build(service.findBioByUsername(req.getId()));
    }

    @Override
    public ResponseEntity<?> save(UserSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsById(req.getUsername()))
            return errorResult.build(
                    "User with username: " + req.getUsername() + " is exists!",
                    HttpStatus.CONFLICT);
        if (service.existsByEmail(req.getEmail()))
            return errorResult.build(
                    "email is used!",
                    HttpStatus.CONFLICT);
        Role role = roleService.findByRoleName(req.getRole());
        Cabang cabang = cabangService.findById(req.getSatker());
        if (cabang == null) return errorResult.build("satker is not valid!", HttpStatus.FORBIDDEN);
        User user = modelMapper.map(req, User.class);
        user.setRole(role);
        user.setCabang(cabang);
        User userSave = service.save(user);
        return saveResult.build(user.getUsername());
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq reqId, UserSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!reqId.getId().equals(req.getUsername()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsById(req.getUsername()))
            return errorResult.build(
                    "User with username: " + req.getUsername() + " is not exists!",
                    HttpStatus.FORBIDDEN);
        Role role = roleService.findByRoleName(req.getRole());
        Cabang cabang = cabangService.findById(req.getSatker());
        if (cabang == null) return errorResult.build("satker is not valid!", HttpStatus.FORBIDDEN);
        User user = modelMapper.map(req, User.class);
        user.setRole(role);
        user.setCabang(cabang);
        User userSave = service.save(user);
        return saveResult.build(userSave.getUsername());
    }

    @Override
    public ResponseEntity<?> updatePassword(FindByIdReq reqId, UserPassUpdateReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!reqId.getId().equals(req.getUsername()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsById(req.getUsername()))
            return errorResult.build(
                    "User with username: " + req.getUsername() + " is not exists!",
                    HttpStatus.FORBIDDEN);
        User user = service.findById(req.getUsername());
        user.setPassword(req.getPassword());
        User userSave = service.save(user);
        return saveResult.build(userSave.getUsername());
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsById(req.getId()))
            return errorResult.build(
                    "User with username: " + req.getId() + " is not exists!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        return singleResult.build("data deleted successfully");
    }

    @Override
    public ResponseEntity<?> saveAll(List<UserSaveReq> reqs, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        List<User> users = new ArrayList<>();
        for (UserSaveReq req : reqs) {
            if (service.existsById(req.getUsername()))
                if (service.existsById(req.getUsername()))
                    return errorResult.build(
                            "User with username: " + req.getUsername() + " is exists!",
                            HttpStatus.CONFLICT);
            if (service.existsByEmail(req.getEmail()))
                return errorResult.build(
                        "email is used!",
                        HttpStatus.CONFLICT);
            Role role = roleService.findByRoleName(req.getRole());
            Cabang cabang = cabangService.findById(req.getSatker());
            if (cabang == null) return errorResult.build("satker is not valid!", HttpStatus.FORBIDDEN);
            User user = modelMapper.map(req, User.class);
            user.setRole(role);
            user.setCabang(cabang);
            users.add(user);
        }
        service.saveAll(users);
        return saveResult.build("batch");
    }
}
