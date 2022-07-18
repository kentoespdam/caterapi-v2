package com.kentoes.caterapi.models.services.role;

import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.role.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
    Page<Role> findAll(PaggingReq req);

    Role findByRoleName(String roleString);

    ERole switchRole(String roleString);

    void saveAll(List<Role> roles);
}
