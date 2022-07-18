package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.munit.MUnit;
import com.kentoes.caterapi.models.entities.munit.MUnitInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MUnitRepository extends JpaRepository<MUnit, String> {
    @Query("SELECT u FROM MUnit u")
    Page<MUnitInfo> findAllInfo(Pageable pageable);

    MUnitInfo findInfoByUnit(String unit);

    @Query("SELECT u FROM MUnit u WHERE u.unit=?1 OR u.name LIKE CONCAT('%',?1,'%') OR u.cabang.satker=?1 OR u.cabang.name LIKE CONCAT('%',?1,'%')")
    Page<MUnitInfo> findPageByUnitOrNameContainsOrCabangSatkerOrCabangName(String name, Pageable pageable);

    boolean existsByUnit(String unit);

    boolean existsByName(String name);
}