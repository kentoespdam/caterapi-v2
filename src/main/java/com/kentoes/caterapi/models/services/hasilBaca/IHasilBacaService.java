package com.kentoes.caterapi.models.services.hasilBaca;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBaca;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBacaInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IHasilBacaService {
    Page<HasilBacaInfo> findAllUncheck(PaggingReq req, Integer periode, ERole posisi);

    HasilBaca findById(Long id);

    HasilBaca save(HasilBaca hasilBaca);

    void saveAll(List<HasilBaca> hasilBacas);

    void delete(Long id);

    boolean existsById(Long id);

    boolean existsByNosamwAndPeriode(String nosamw, Integer periode);
}
