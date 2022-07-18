package com.kentoes.caterapi.models.services.confirmLog;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLog;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLogInfo;
import com.kentoes.caterapi.models.repositories.ConfirmLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfirmLogService implements IConfirmLogService {
    @Autowired
    private ConfirmLogRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<ConfirmLogInfo> findAll(String username, Integer periode, PaggingReq req) {
        pageable = pageableBuilder.build(req);
        return repository.findAllInfo(username, periode, pageable);
    }

    @Override
    public ConfirmLog save(ConfirmLog confirmLog) {
        return repository.save(confirmLog);
    }

    @Override
    public void saveAll(List<ConfirmLog> confirmLogs) {

    }

    @Override
    public void delete(Long id) {
        Optional<ConfirmLog> confirmLog = repository.findById(id);
        if (!confirmLog.isPresent()) return;
        repository.delete(confirmLog.get());
    }

    @Override
    public boolean existsByPetugasUsernameAndHasilBacaId(String username, Long id) {
        return repository.existsByPetugasUsernameAndHasilBacaId(username, id);
    }
}
