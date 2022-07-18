package com.kentoes.caterapi.models.services.statSmb;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.mStatSmb.MStatSmb;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMStatSmbService {
    Page<MStatSmb> findAll(PaggingReq req);

    MStatSmb findById(String id);

    MStatSmb save(MStatSmb mStatSmb);

    void saveAll(List<MStatSmb> mStatSmbs);

    void delete(String id);

    boolean existsById(String id);
}
