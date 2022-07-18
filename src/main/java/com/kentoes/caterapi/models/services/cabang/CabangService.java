package com.kentoes.caterapi.models.services.cabang;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import com.kentoes.caterapi.models.repositories.CabangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CabangService implements ICabangService {
    @Autowired
    private CabangRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<Cabang> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch()))
            return repository.findBySatkerOrNameContains(req.getSearch(), pageable);
        return repository.findAll(pageable);
    }

    @Override
    public Cabang findById(String id) {
        Optional<Cabang> cabang = repository.findById(id);
        if (!cabang.isPresent()) return null;
        return cabang.get();
    }

    @Override
    public boolean existsBySatker(String satker) {
        return repository.existsBySatker(satker);
    }

    @Override
    public Cabang save(Cabang cabang) {
        return repository.save(cabang);
    }

    @Override
    public void saveAll(List<Cabang> cabangs) {
        repository.saveAll(cabangs);
    }

    @Override
    public void delete(String id) {

    }
}
