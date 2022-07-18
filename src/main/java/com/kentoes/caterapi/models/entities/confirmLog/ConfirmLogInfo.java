package com.kentoes.caterapi.models.entities.confirmLog;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.enums.EConfirm;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.hasilBaca.HasilBacaJoin;
import com.kentoes.caterapi.models.entities.user.UserJoin;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "hasilBaca", "petugas", "role", "confirm", "keterangan", "createdAt", "updatedAt"})
public interface ConfirmLogInfo {

    Long getId();

    HasilBacaJoin getHasilBaca();

    UserJoin getPetugas();

    ERole getRole();

    EConfirm getConfirm();

    String getKeterangan();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
