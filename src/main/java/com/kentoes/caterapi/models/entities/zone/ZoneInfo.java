package com.kentoes.caterapi.models.entities.zone;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.cabang.CabangJoin;
import com.kentoes.caterapi.models.entities.user.UserJoin;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "name", "address", "target", "terbaca", "progress", "user", "cabang", "createdAt", "updatedAt"})
public interface ZoneInfo {
    String getId();

    String getName();

    String getAddress();

    Integer getTarget();

    Integer getTerbaca();

    Float getProgress();

    UserJoin getUser();

    CabangJoin getCabang();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

}
