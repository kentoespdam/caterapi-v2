package com.kentoes.caterapi.models.entities.munit;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.cabang.CabangJoin;

import java.time.LocalDateTime;

@JsonPropertyOrder({"unit", "name", "cabang", "createdAt", "updatedAt"})
public interface MUnitInfo {
    String getUnit();

    String getName();

    String getAddress();

    CabangJoin getCabang();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
