package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.Quiz;
import com.carlos.usuarios.beusers.repositories.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService{
    
    @Autowired
    private QuizRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Quiz> findAll(){
        return (List<Quiz>) repository.findAll();
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Quiz save(Quiz anexoMaterial) {
        return repository.save(anexoMaterial);
    }

    @Override
    public Optional<Quiz> update(Quiz quiz, Long id) {
        Optional<Quiz> o = this.findById(id);
        Quiz quizOptional = null;
        if(o.isPresent()){
            Quiz quizDb = o.orElseThrow();
            quizDb.setNombre(quiz.getNombre());
            quizDb.setMateria(quiz.getMateria());
            quizOptional = this.save(quizDb);
        }
        return Optional.ofNullable(quizOptional);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
