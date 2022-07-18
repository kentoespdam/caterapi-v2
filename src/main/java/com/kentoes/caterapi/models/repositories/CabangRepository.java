package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.cabang.Cabang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CabangRepository extends JpaRepository<Cabang, String> {
    Optional<Cabang> findById(String satker);

    @Query("SELECT c FROM Cabang c WHERE c.satker=?1 OR c.name LIKE CONCAT('%',?1,'%')")
    Page<Cabang> findBySatkerOrNameContains(String name, Pageable pageable);

    boolean existsBySatker(String satker);
}