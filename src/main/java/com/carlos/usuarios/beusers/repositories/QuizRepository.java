package com.carlos.usuarios.beusers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carlos.usuarios.beusers.models.entities.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Long>{
    
}
