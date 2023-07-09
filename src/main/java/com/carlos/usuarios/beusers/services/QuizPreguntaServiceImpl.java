package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.QuizPregunta;
import com.carlos.usuarios.beusers.repositories.QuizPreguntaRepository;

@Service
public class QuizPreguntaServiceImpl implements QuizPreguntaService{
    
    @Autowired
    private QuizPreguntaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<QuizPregunta> findAll(){
        return (List<QuizPregunta>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuizPregunta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public QuizPregunta save(QuizPregunta quizPregunta) {
        return repository.save(quizPregunta);
    }

    @Override
    @Transactional
    public Optional<QuizPregunta> update(QuizPregunta quizPregunta, Long id) {
        
        System.out.println("------");
        System.out.println(quizPregunta.getQuiz());
        System.out.println("---");
        Optional<QuizPregunta> o = this.findById(id);
        QuizPregunta quizPreguntaOptional = null;
        if(o.isPresent()){
            QuizPregunta quizPreguntaDb = o.orElseThrow();
            quizPreguntaDb.setImagen(quizPregunta.getImagen());
            quizPreguntaDb.setPregunta1(quizPregunta.getPregunta1());
            quizPreguntaDb.setPregunta2(quizPregunta.getPregunta2());
            quizPreguntaDb.setPregunta3(quizPregunta.getPregunta3());
            quizPreguntaDb.setPregunta4(quizPregunta.getPregunta4());
            quizPreguntaDb.setQuiz(quizPregunta.getQuiz());
            quizPreguntaDb.setRta(quizPregunta.getRta());
            quizPreguntaOptional = this.save(quizPreguntaDb);
        }
        return Optional.ofNullable(quizPreguntaOptional);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
