package com.kentoes.caterapi.models.services.kondisi;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.entities.kondisi.Kondisi;
import com.kentoes.caterapi.models.repositories.KondisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KondisiService implements IKondisiService {
    @Autowired
    private KondisiRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<Kondisi> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch()))
            return repository.findKondisiByKodeOrKondisiContains(req.getSearch(), pageable);
        return repository.findAll(pageable);
    }

    @Override
    public Kondisi findById(Long id) {
        Optional<Kondisi> kondisi = repository.findById(id);
        if (!kondisi.isPresent()) return null;
        return kondisi.get();
    }

    @Override
    public Kondisi findByKode(String kode) {
        Optional<Kondisi> kondisi = repository.findByKode(kode);
        if (!kondisi.isPresent()) return null;
        return kondisi.get();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByKode(String kode) {
        return repository.existsByKode(kode);
    }

    @Override
    public Kondisi save(Kondisi kondisi) {
        return repository.save(kondisi);
    }

    @Override
    public void saveAll(List<Kondisi> kondisis) {
        repository.saveAll(kondisis);
    }

    @Override
    public void delete(Long id) {
        Optional<Kondisi> kondisi = repository.findById(id);
        if (!kondisi.isPresent()) return;
        repository.delete(kondisi.get());
    }
}
