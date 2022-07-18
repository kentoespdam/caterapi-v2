package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.customer.Customer;
import com.kentoes.caterapi.models.entities.customer.CustomerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @EntityGraph(attributePaths = {"zone", "statSmb"})
    @Query("SELECT c FROM Customer c")
    Page<CustomerInfo> findAllInfo(Pageable pageable);

    @EntityGraph(attributePaths = {"zone", "statSmb"})
    @Query("SELECT c FROM Customer c WHERE " +
            "c.nosamw=?1 " +
            "OR c.nama LIKE CONCAT('%',?1,'%') " +
            "OR c.alamat LIKE CONCAT('%',?1,'%') " +
            "OR c.jlw=?1 " +
            "OR c.urjlwp LIKE CONCAT('%',?1,'%') " +
            "OR c.zone.name LIKE CONCAT('%',?1,'%') " +
            "OR c.statSmb.statSmb=?1 " +
            "OR c.statSmb.urstatSmb LIKE CONCAT('%',?1,'%')")
    Page<CustomerInfo> findBySearch(String search, Pageable pageable);

    Optional<Customer> findByNosamw(String nosamw);

    Optional<CustomerInfo> findInfoByNosamw(String nosamw);

    boolean existsByNosamw(String nosamw);
}