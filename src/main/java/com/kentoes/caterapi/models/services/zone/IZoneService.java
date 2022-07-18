package com.kentoes.caterapi.models.services.zone;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.zone.Zone;
import com.kentoes.caterapi.models.entities.zone.ZoneInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IZoneService {
    Page<ZoneInfo> findAll(PaggingReq req);

    Zone findById(String id);

    ZoneInfo findInfoById(String id);

    boolean existsById(String id);

    boolean existsByName(String name);

    Zone save(Zone zone);

    void saveAll(List<Zone> zones);

    void delete(String id);
}
