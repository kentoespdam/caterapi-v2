package com.kentoes.caterapi.models.services.role;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.role.Role;
import com.kentoes.caterapi.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<Role> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        return repository.findAll(pageable);
    }

    @Override
    public Role findByRoleName(String roleString) {
        ERole eRole = switchRole(roleString);
        System.out.println("eRole : " + eRole);
        Optional<Role> role = repository.findByRoleName(eRole);
        System.out.println("present");
        System.out.println(role.isPresent());
        if (!role.isPresent()) return null;
        return role.get();
    }

    @Override
    public ERole switchRole(String roleString) {
        ERole eRole = null;
        switch (roleString) {
            case "ROLE_ADMIN":
                eRole = ERole.ROLE_ADMIN;
                break;
            case "ROLE_CHECKER_TI":
                eRole = ERole.ROLE_CHECKER_TI;
                break;
            case "ROLE_CHECKER_CABANG":
                eRole = ERole.ROLE_CHECKER_CABANG;
                break;
            case "ROLE_CHECKER_KOPERASI":
                eRole = ERole.ROLE_CHECKER_KOPERASI;
                break;
            case "ROLE_CATER":
                eRole = ERole.ROLE_CATER;
                break;
        }
        return eRole;
    }

    @Override
    public void saveAll(List<Role> roles) {
        repository.saveAll(roles);
    }
}
