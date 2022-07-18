package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.enums.ERole;
import com.kentoes.caterapi.models.entities.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}