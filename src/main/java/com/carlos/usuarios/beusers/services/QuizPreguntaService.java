package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.QuizPregunta;

public interface QuizPreguntaService {
    
    List<QuizPregunta> findAll();
    
    Optional<QuizPregunta> findById(Long id);

    QuizPregunta save(QuizPregunta quizPregunta);

    Optional<QuizPregunta> update(QuizPregunta quizPregunta, Long id);

    void remove(Long id); 
}
