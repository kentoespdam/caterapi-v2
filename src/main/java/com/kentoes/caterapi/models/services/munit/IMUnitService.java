package com.kentoes.caterapi.models.services.munit;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.munit.MUnit;
import com.kentoes.caterapi.models.entities.munit.MUnitInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMUnitService {
    Page<MUnitInfo> findAll(PaggingReq req);

    MUnit findById(String id);


    boolean existsByUnit(String unit);

    boolean existsByName(String name);

    MUnit save(MUnit mUnit);

    void saveAll(List<MUnit> mUnits);

    void delete(String unit);
}
