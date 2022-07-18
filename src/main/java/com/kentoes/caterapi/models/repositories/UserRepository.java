package com.kentoes.caterapi.models.repositories;

import com.kentoes.caterapi.models.entities.user.User;
import com.kentoes.caterapi.models.entities.user.UserAcc;
import com.kentoes.caterapi.models.entities.user.UserBio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {"role", "cabang"})
    @Query("SELECT u FROM User u")
    Page<UserBio> findBioAll(Pageable pageable);

    @EntityGraph(attributePaths = {"role", "cabang"})
    @Query("SELECT u FROM User u WHERE u.username=?1 OR u.fullName LIKE CONCAT('%',?1,'%') OR u.cabang.satker=?1 OR u.cabang.name LIKE CONCAT('%',?1,'%') OR u.role.roleName LIKE CONCAT('%',?1,'%')")
    Page<UserBio> findByUsernameOrFullNameOrCabangOrRole(String search, Pageable pageable);

    boolean existsByEmail(String email);

    Optional<UserAcc> findByUsername(String username);

    Optional<UserBio> findBioByUsername(String username);
}