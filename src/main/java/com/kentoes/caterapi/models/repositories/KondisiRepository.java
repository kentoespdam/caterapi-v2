package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.kondisi.Kondisi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KondisiRepository extends JpaRepository<Kondisi, Long> {
    @Query("SELECT k FROM Kondisi k WHERE k.kode=?1 OR k.kondisi LIKE CONCAT('%',?1,'%')")
    Page<Kondisi> findKondisiByKodeOrKondisiContains(String search, Pageable pageable);

    Optional<Kondisi> findByKode(String kode);

    boolean existsByKode(String kode);
}