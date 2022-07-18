package com.kentoes.caterapi.models.services.hasilBaca;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBaca;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBacaInfo;
import com.kentoes.caterapi.models.repositories.HasilBacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HasilBacaService implements IHasilBacaService {
    @Autowired
    private HasilBacaRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<HasilBacaInfo> findAllUncheck(PaggingReq req, Integer periode, ERole posisi) {
        pageable = pageableBuilder.build(req);
        return repository.findByPeriodeAndPosisi(periode, posisi, pageable);
    }

    @Override
    public HasilBaca findById(Long id) {
        Optional<HasilBaca> hasilBaca = repository.findById(id);
        if (!hasilBaca.isPresent()) return null;
        return hasilBaca.get();
    }

    @Override
    public HasilBaca save(HasilBaca hasilBaca) {
        return repository.save(hasilBaca);
    }

    @Override
    public void saveAll(List<HasilBaca> hasilBacas) {
        repository.saveAll(hasilBacas);
    }

    @Override
    public void delete(Long id) {
        Optional<HasilBaca> hasilBaca = repository.findById(id);
        if (!hasilBaca.isPresent()) return;
        repository.delete(hasilBaca.get());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNosamwAndPeriode(String nosamw, Integer periode) {
        return repository.existsByCustomerNosamwAndPeriode(nosamw, periode);
    }
}
