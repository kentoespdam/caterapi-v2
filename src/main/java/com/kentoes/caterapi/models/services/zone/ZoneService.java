package com.kentoes.caterapi.models.services.zone;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.user.User;
import com.kentoes.caterapi.models.entities.zone.Zone;
import com.kentoes.caterapi.models.entities.zone.ZoneInfo;
import com.kentoes.caterapi.models.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ZoneService implements IZoneService {
    @Autowired
    private ZoneRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<ZoneInfo> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch()))
            return repository.findByZoneNameOrAddressOrUserOrCabang(req.getSearch(), pageable);
        return repository.findZoneAll(pageable);
    }

    @Override
    public Zone findById(String id) {
        Optional<Zone> zone = repository.findById(id);
        if (!zone.isPresent()) return null;
        return zone.get();
    }

    @Override
    public ZoneInfo findInfoById(String id) {
        Optional<ZoneInfo> zone = repository.findInfoById(id);
        if (!zone.isPresent()) return null;
        return zone.get();
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public Zone save(Zone zone) {
        return repository.save(zone);
    }

    @Override
    public void saveAll(List<Zone> zones) {
        repository.saveAll(zones);
    }

    @Override
    public void delete(String id) {
        Optional<Zone> zone = repository.findById(id);
        if (!zone.isPresent()) return;
        repository.delete(zone.get());
    }
}
