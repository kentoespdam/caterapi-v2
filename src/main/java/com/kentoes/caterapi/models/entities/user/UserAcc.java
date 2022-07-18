package com.kentoes.caterapi.models.entities.user;

import com.kentoes.caterapi.models.entities.role.RoleJoin;

public interface UserAcc {
    String getUsername();

    String getPassword();

    boolean isEnabled();

    RoleJoin getRole();
}
