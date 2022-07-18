package com.kentoes.caterapi.models.services.cabang;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.cabang.Cabang;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICabangService {
    Page<Cabang> findAll(PaggingReq paggingReq);

    Cabang findById(String id);

    boolean existsBySatker(String satker);

    Cabang save(Cabang cabang);

    void saveAll(List<Cabang> cabangs);

    void delete(String id);

}
