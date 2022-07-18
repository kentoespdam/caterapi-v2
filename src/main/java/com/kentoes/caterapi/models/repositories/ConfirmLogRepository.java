package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLog;
import com.kentoes.caterapi.models.entities.confirmLog.ConfirmLogInfo;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBacaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfirmLogRepository extends JpaRepository<ConfirmLog, Long> {
    @Query("SELECT cl FROM ConfirmLog cl WHERE cl.petugas.username=?1 AND cl.hasilBaca.periode=?2")
    Page<ConfirmLogInfo> findAllInfo(String username, Integer periode, Pageable pageable);

    boolean existsByPetugasUsernameAndHasilBacaId(String username, Long id);
}