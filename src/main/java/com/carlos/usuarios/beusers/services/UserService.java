package com.carlos.usuarios.beusers.services;
import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.User;

public interface UserService {
    
    List<User> findAll();
    
    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> update(User user, Long id);

    void remove(Long id);
}
