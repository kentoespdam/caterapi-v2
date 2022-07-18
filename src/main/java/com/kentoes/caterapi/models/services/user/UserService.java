package com.kentoes.caterapi.models.services.user;

import com.kentoes.caterapi.config.PageableBuilder;
import com.kentoes.caterapi.controllers.dto.request.PaggingReq;
import com.kentoes.caterapi.models.entities.user.User;
import com.kentoes.caterapi.models.entities.user.UserAcc;
import com.kentoes.caterapi.models.entities.user.UserBio;
import com.kentoes.caterapi.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;
    private Pageable pageable = null;
    private PageableBuilder pageableBuilder = new PageableBuilder();

    @Override
    public Page<UserBio> findAll(PaggingReq req) {
        pageable = pageableBuilder.build(req);
        if (Objects.nonNull(req.getSearch()))
            return repository.findByUsernameOrFullNameOrCabangOrRole(req.getSearch(), pageable);
        return repository.findBioAll(pageable);
    }

    @Override
    public User findById(String username) {
        Optional<User> user = repository.findById(username);
        if (!user.isPresent()) return null;
        return user.get();
    }

    @Override
    public UserAcc findByUsername(String username) {
        Optional<UserAcc> user = repository.findByUsername(username);
        if (!user.isPresent()) return null;
        return user.get();
    }

    @Override
    public UserBio findBioByUsername(String username) {
        Optional<UserBio> user = repository.findBioByUsername(username);
        if (!user.isPresent()) return null;
        return user.get();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    public boolean existsById(String username) {
        return repository.existsById(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
