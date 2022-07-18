package com.kentoes.caterapi.models.entities.confirmLog;

import com.kentoes.caterapi.models.entities.enums.EConfirm;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.user.UserJoin;

public interface ConfirmLogJoin {
    Long getId();

    ERole getRole();

    EConfirm getConfirm();

    String getKeterangan();

    UserJoin getPetugas();
}
