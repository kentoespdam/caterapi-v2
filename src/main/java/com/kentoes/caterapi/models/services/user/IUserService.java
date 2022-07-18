package com.kentoes.caterapi.models.services.user;

import com.kentoes.caterapi.controllers.dto.request.FindByIdReq;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.user.User;
import com.kentoes.caterapi.models.entities.user.UserAcc;
import com.kentoes.caterapi.models.entities.user.UserBio;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    Page<UserBio> findAll(PaggingReq req);

    User findById(String username);

    UserAcc findByUsername(String username);

    UserBio findBioByUsername(String username);

    User save(User user);

    void saveAll(List<User> users);

    boolean existsById(String username);

    boolean existsByEmail(String email);
}
