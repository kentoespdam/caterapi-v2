package com.kentoes.caterapi.models.services.statSmb;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmb;
import com.kentoes.caterapi.models.repositories.MStatSmbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MStatSmbService implements IMStatSmbService {
    @Autowired
    private MStatSmbRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<MStatSmb> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch()))
            return repository.findByStatSmbOrUrstatSmbContains(req.getSearch(), pageable);
        return repository.findAll(pageable);
    }

    @Override
    public MStatSmb findById(String id) {
        Optional<MStatSmb> mStatSmb = repository.findById(id);
        if (!mStatSmb.isPresent()) return null;
        return mStatSmb.get();
    }

    @Override
    public MStatSmb save(MStatSmb mStatSmb) {
        return repository.save(mStatSmb);
    }

    @Override
    public void saveAll(List<MStatSmb> mStatSmbs) {
        repository.saveAll(mStatSmbs);
    }

    @Override
    public void delete(String id) {
        Optional<MStatSmb> mStatSmb = repository.findById(id);
        if (!mStatSmb.isPresent()) return;
        repository.delete(mStatSmb.get());
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
