package com.kentoes.caterapi.models.services.munit;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.munit.MUnit;
import com.kentoes.caterapi.models.entities.munit.MUnitInfo;
import com.kentoes.caterapi.models.repositories.MUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MUnitService implements IMUnitService {
    @Autowired
    private MUnitRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<MUnitInfo> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch())) {
            return repository.findPageByUnitOrNameContainsOrCabangSatkerOrCabangName(req.getSearch(), pageable);
        }
        return repository.findAllInfo(pageable);
    }

    @Override
    public MUnit findById(String id) {
        Optional<MUnit> mUnit = repository.findById(id);
        if (!mUnit.isPresent()) return null;
        return mUnit.get();
    }

    @Override
    public boolean existsByUnit(String unit) {
        return repository.existsByUnit(unit);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public MUnit save(MUnit mUnit) {
        return repository.save(mUnit);
    }

    @Override
    public void saveAll(List<MUnit> mUnits) {
        repository.saveAll(mUnits);
    }

    @Override
    public void delete(String unit) {
        MUnit mUnit = findById(unit);
        repository.delete(mUnit);
    }
}
