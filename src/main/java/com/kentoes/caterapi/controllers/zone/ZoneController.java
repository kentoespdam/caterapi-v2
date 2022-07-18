package com.kentoes.caterapi.controllers.zone;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.entities.user.User;
import com.kentoes.caterapi.models.entities.zone.Zone;
import com.kentoes.caterapi.models.services.cabang.CabangService;
import com.kentoes.caterapi.models.services.user.UserService;
import com.kentoes.caterapi.models.services.zone.ZoneService;
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
@RequestMapping("/api/zones")
public class ZoneController implements IZoneController, BaseController {
    @Autowired
    private ZoneService service;
    @Autowired
    private UserService userService;
    @Autowired
    private CabangService cabangService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req) {
        return pageResult.build(httpServletRequest, service.findAll(req), req);
    }

    @Override
    public ResponseEntity<?> findById(FindByIdReq req) {
        return singleResult.build(service.findInfoById(req.getId()));
    }

    @Override
    public ResponseEntity<?> save(ZoneSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsById(req.getId()) || service.existsByName(req.getName()))
            return errorResult.build(
                    "Zone id/name with id: " + req.getId() + " is exists!",
                    HttpStatus.CONFLICT);
        if (!userService.existsById(req.getUserId()))
            return errorResult.build(
                    "invalid userId!",
                    HttpStatus.FORBIDDEN);
        if (!cabangService.existsBySatker(req.getSatker()))
            return errorResult.build(
                    "invalid satker!",
                    HttpStatus.FORBIDDEN);
        User user = userService.findById(req.getUserId());
        Cabang cabang = cabangService.findById(req.getSatker());
        Zone zone = modelMapper.map(req, Zone.class);
        zone.setUser(user);
        zone.setCabang(cabang);
        Zone zoneSave = service.save(zone);
        return saveResult.build(zoneSave.getId());
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq reqId, ZoneSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!reqId.getId().equals(req.getId()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsById(req.getId()))
            return errorResult.build(
                    "Zone with id: " + req.getId() + " is not exists!",
                    HttpStatus.CONFLICT);
        if (!userService.existsById(req.getUserId()))
            return errorResult.build(
                    "invalid userId!",
                    HttpStatus.FORBIDDEN);
        if (!cabangService.existsBySatker(req.getSatker()))
            return errorResult.build(
                    "invalid satker!",
                    HttpStatus.FORBIDDEN);
        User user = userService.findById(req.getUserId());
        Cabang cabang = cabangService.findById(req.getSatker());
        Zone zone = modelMapper.map(req, Zone.class);
        zone.setUser(user);
        zone.setCabang(cabang);
        Zone zoneSave = service.save(zone);
        return saveResult.build(zoneSave.getId());
    }

    @Override
    public ResponseEntity<?> saveAll(List<ZoneSaveReq> reqs, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        List<Zone> zones = new ArrayList<>();
        for (ZoneSaveReq req : reqs) {
            if (errors.hasErrors()) return errorResult.build(errors);
            if (service.existsById(req.getId()) || service.existsByName(req.getName()))
                return errorResult.build(
                        "Zone id/name with id: " + req.getId() + " is exists!",
                        HttpStatus.CONFLICT);
            if (!userService.existsById(req.getUserId()))
                return errorResult.build(
                        "invalid userId!",
                        HttpStatus.FORBIDDEN);
            if (!cabangService.existsBySatker(req.getSatker()))
                return errorResult.build(
                        "invalid satker!",
                        HttpStatus.FORBIDDEN);
            User user = userService.findById(req.getUserId());
            Cabang cabang = cabangService.findById(req.getSatker());
            Zone zone = modelMapper.map(req, Zone.class);
            zone.setUser(user);
            zone.setCabang(cabang);
            zones.add(zone);
        }
        service.saveAll(zones);
        return saveResult.build("batch");
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq req) {
        if (Objects.isNull(req))
            return errorResult.build("Invalid zone id!", HttpStatus.BAD_REQUEST);
        if (!service.existsById(req.getId()))
            return errorResult.build(
                    "Zone with id:" + req.getId() + " is not exist",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        service.delete(req.getId());
        return singleResult.build("data deleted successfully");
    }
}
