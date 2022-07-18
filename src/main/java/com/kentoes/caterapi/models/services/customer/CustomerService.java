package com.kentoes.caterapi.models.services.customer;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.customer.Customer;
import com.kentoes.caterapi.models.entities.customer.CustomerInfo;
import com.kentoes.caterapi.models.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<CustomerInfo> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch())) {
            return repository.findBySearch(req.getSearch(), pageable);
        }
        return repository.findAllInfo(pageable);
    }

    @Override
    public Customer findById(String noreg) {
        Optional<Customer> customer = repository.findById(noreg);
        if (!customer.isPresent()) return null;
        return customer.get();
    }

    @Override
    public Customer findByNosamw(String nosamw) {
        Optional<Customer> customer = repository.findByNosamw(nosamw);
        if (!customer.isPresent()) return null;
        return customer.get();
    }

    @Override
    public CustomerInfo findInfoByNosamw(String nosamw) {
        Optional<CustomerInfo> customer = repository.findInfoByNosamw(nosamw);
        if (!customer.isPresent()) return null;
        return customer.get();
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void saveAll(List<Customer> customers) {
        repository.saveAll(customers);
    }

    @Override
    public void delete(String id) {
        Optional<Customer> customer = repository.findById(id);
        if (!customer.isPresent()) return;
        repository.delete(customer.get());
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNosamw(String nosamw) {
        return repository.existsByNosamw(nosamw);
    }
}
