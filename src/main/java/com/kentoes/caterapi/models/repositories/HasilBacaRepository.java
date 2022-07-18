package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBaca;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBacaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HasilBacaRepository extends JpaRepository<HasilBaca, Long> {
    @EntityGraph(attributePaths = {"customer", "kondisi", "confirmLogs"})
    @Query("SELECT h FROM HasilBaca h")
    Page<HasilBacaInfo> findAllInfo(Pageable pageable);

//    @EntityGraph(attributePaths = {"customer", "kondisi", "confirmLogs"})
//    @Query("SELECT h FROM HasilBaca h " +
//            "WHERE periode=:s.periode " +
//            "AND (h.checkDate BETWEEN :s.fromDate AND :s.toDate) " +
//            "OR h.nosamw=:s.nosamw " +
//            "OR h.customer.nama LIKE CONCAT('%',:s.nama,'%')")
//    Page<HasilBacaInfo> findBySearch(@PathParam("s") HasilBacaSearchReq search, Pageable pageable);

    @EntityGraph(attributePaths = {"customer", "kondisi", "confirmLogs"})
    Page<HasilBacaInfo> findByPeriodeAndPosisi(Integer periode, ERole posisi, Pageable pageable);

    boolean existsByCustomerNosamwAndPeriode(String nosamw, Integer periode);
}