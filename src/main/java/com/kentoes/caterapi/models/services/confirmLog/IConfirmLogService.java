package com.kentoes.caterapi.models.services.confirmLog;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLog;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLogInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IConfirmLogService {
    Page<ConfirmLogInfo> findAll(String username, Integer periode, PaggingReq req);

    ConfirmLog save(ConfirmLog confirmLog);

    void saveAll(List<ConfirmLog> confirmLogs);

    void delete(Long id);

    boolean existsByPetugasUsernameAndHasilBacaId(String username, Long id);
}
