package com.kentoes.caterapi.controllers.customer;

import com.kentoes.caterapi.controllers.BaseController;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.customer.Customer;
import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmb;
import com.kentoes.caterapi.models.entities.zone.Zone;
import com.kentoes.caterapi.models.services.customer.CustomerService;
import com.kentoes.caterapi.models.services.statSmb.MStatSmbService;
import com.kentoes.caterapi.models.services.user.UserService;
import com.kentoes.caterapi.models.services.zone.ZoneService;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/api/customers")
public class CustomerController implements ICustomerController, BaseController {
    @Autowired
    private CustomerService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private MStatSmbService mStatSmbService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> findAll(HttpServletRequest httpServletRequest, PaggingReq req) {
        return pageResult.build(httpServletRequest, service.findAll(req), req);
    }

    @Override
    public ResponseEntity<?> findById(FindByIdReq req) {
        return singleResult.build(service.findInfoByNosamw(req.getId()));
    }

    @Override
    public ResponseEntity<?> save(CustomerSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (service.existsById(req.getNoreg()))
            return errorResult.build(
                    "Customer with noreg: " + req.getNoreg() + " is exist!",
                    HttpStatus.CONFLICT);
        if (service.existsByNosamw(req.getNosamw()))
            return errorResult.build(
                    "Customer with nosamw: " + req.getNosamw() + " is exist!",
                    HttpStatus.CONFLICT);
        Zone zone = zoneService.findById(req.getZoneId());
        if (zone == null) return errorResult.build(
                "invalid zone!",
                HttpStatus.CONFLICT);
        MStatSmb mStatSmb = mStatSmbService.findById(req.getStatSmbId());
        if (mStatSmb == null) return errorResult.build(
                "invalid stat smb!",
                HttpStatus.CONFLICT);
        Customer customer = modelMapper.map(req, Customer.class);
        Customer customerSave = service.save(customer);
        return saveResult.build(customerSave.getNoreg());
    }

    @Override
    public ResponseEntity<?> update(FindByIdReq reqId, CustomerSaveReq req, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        if (!reqId.getId().equals(req.getNoreg()))
            return errorResult.build("Invalid data request", HttpStatus.NOT_ACCEPTABLE);
        if (!service.existsById(req.getNoreg()))
            return errorResult.build(
                    "Customer with noreg: " + req.getNoreg() + " is not exist!",
                    HttpStatus.CONFLICT);
        Zone zone = zoneService.findById(req.getZoneId());
        if (zone == null) return errorResult.build(
                "invalid zone!",
                HttpStatus.CONFLICT);
        MStatSmb mStatSmb = mStatSmbService.findById(req.getStatSmbId());
        if (mStatSmb == null) return errorResult.build(
                "invalid stat smb!",
                HttpStatus.CONFLICT);
        Customer customer = modelMapper.map(req, Customer.class);
        customer.setZone(zone);
        customer.setStatSmb(mStatSmb);
        Customer customerSave = service.save(customer);
        return saveResult.build(customerSave.getNoreg());
    }

    @Override
    public ResponseEntity<?> saveAll(List<CustomerSaveReq> reqs, Errors errors) {
        if (errors.hasErrors()) return errorResult.build(errors);
        List<Customer> customers = new ArrayList<>();
        for (CustomerSaveReq req : reqs) {
            Zone zone = zoneService.findById(req.getZoneId());
            if (zone == null) return errorResult.build(
                    "invalid zone!",
                    HttpStatus.CONFLICT);
            MStatSmb mStatSmb = mStatSmbService.findById(req.getStatSmbId());
            if (mStatSmb == null) return errorResult.build(
                    "invalid stat smb!",
                    HttpStatus.CONFLICT);
            Customer customer = modelMapper.map(req, Customer.class);
            customer.setZone(zone);
            customer.setStatSmb(mStatSmb);
            customers.add(customer);
        }
        service.saveAll(customers);
        return saveResult.build("batch");
    }

    @Override
    public ResponseEntity<?> delete(FindByIdReq req) {
        if (Objects.isNull(req))
            return errorResult.build("invalid customer id!", HttpStatus.BAD_REQUEST);
        if (!service.existsById(req.getId()))
            return errorResult.build("customer is not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        service.delete(req.getId());
        return singleResult.build("data deleted successfully");
    }
}
