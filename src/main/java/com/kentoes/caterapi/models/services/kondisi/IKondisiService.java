package com.kentoes.caterapi.models.services.kondisi;

import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.kondisi.Kondisi;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IKondisiService {
    Page<Kondisi> findAll(PaggingReq req);

    Kondisi findById(Long id);

    Kondisi findByKode(String kode);

    boolean existsById(Long id);

    boolean existsByKode(String kode);

    Kondisi save(Kondisi kondisi);

    void saveAll(List<Kondisi> kondisis);

    void delete(Long id);
}
