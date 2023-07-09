package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.Quiz;

public interface QuizService {
    List<Quiz> findAll();
    
    Optional<Quiz> findById(Long id);

    Quiz save(Quiz quiz);

    Optional<Quiz> update(Quiz quiz, Long id);

    void remove(Long id); 
}
