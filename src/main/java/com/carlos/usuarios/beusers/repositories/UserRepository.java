package com.carlos.usuarios.beusers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carlos.usuarios.beusers.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
