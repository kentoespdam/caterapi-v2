package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MStatSmbRepository extends JpaRepository<MStatSmb, String> {
    @Query("SELECT s FROM MStatSmb s WHERE s.statSmb=?1 OR s.urstatSmb LIKE CONCAT('%',?1,'%')")
    Page<MStatSmb> findByStatSmbOrUrstatSmbContains(String search, Pageable pageable);
}