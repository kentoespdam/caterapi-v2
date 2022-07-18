package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.zone.Zone;
import com.kentoes.caterapi.models.entities.zone.ZoneInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, String> {
    @EntityGraph(attributePaths = {"cabang", "user"})
    @Query("SELECT z FROM Zone z")
    Page<ZoneInfo> findZoneAll(Pageable pageable);

    @Query("SELECT z FROM Zone z WHERE " +
            "z.name LIKE CONCAT('%',?1,'%') " +
            "OR z.address LIKE CONCAT('%',?1,'%') " +
            "OR z.user.username=?1 " +
            "OR z.user.fullName LIKE CONCAT('%',?1,'%') " +
            "OR z.cabang.satker=?1 " +
            "OR z.cabang.name LIKE CONCAT('%',?1,'%')")
    Page<ZoneInfo> findByZoneNameOrAddressOrUserOrCabang(String search, Pageable pageable);

    Optional<ZoneInfo> findInfoById(String id);

    boolean existsByName(String name);
}