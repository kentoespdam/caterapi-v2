package com.kentoes.caterapi.models.services.customer;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.customer.Customer;
import com.kentoes.caterapi.models.entities.customer.CustomerInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Page<CustomerInfo> findAll(PaggingReq req);

    Customer findById(String noreg);

    Customer findByNosamw(String nosamw);

    CustomerInfo findInfoByNosamw(String nosamw);

    Customer save(Customer customer);

    void saveAll(List<Customer> customers);

    void delete(String id);

    boolean existsById(String id);

    boolean existsByNosamw(String nosamw);
}
