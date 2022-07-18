package com.kentoes.caterapi.models.entities.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.caterapi.models.entities.cabang.CabangJoin;
import com.kentoes.caterapi.models.entities.role.RoleJoin;

import java.time.LocalDateTime;

@JsonPropertyOrder({"username", "fullName", "email", "address", "phone", "cabang", "role", "enabled", "createdAt", "updatedAt"})
public interface UserBio {
    String getUsername();

    String getFullName();

    String getEmail();

    String getAddress();

    String getPhone();

    RoleJoin getRole();

    CabangJoin getCabang();

    boolean isEnabled();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
