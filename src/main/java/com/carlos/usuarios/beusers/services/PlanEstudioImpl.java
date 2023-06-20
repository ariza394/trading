package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.PlanEstudio;
import com.carlos.usuarios.beusers.repositories.PlanEstudioRepository;

@Service
public class PlanEstudioImpl implements PlanEstudioService{
    
    @Autowired
    private PlanEstudioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<PlanEstudio> findAll() {
       return (List<PlanEstudio>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlanEstudio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public PlanEstudio save(PlanEstudio estudio) {
        return repository.save(estudio);
    }

    @Override
    @Transactional
    public Optional<PlanEstudio> update(PlanEstudio estudio, Long id) {
        Optional<PlanEstudio> o = this.findById(id);
        PlanEstudio estudioOptional = null;
        if(o.isPresent()){
            PlanEstudio estudioDb = o.orElseThrow();
            estudioDb.setNombre(estudio.getNombre());
            estudioDb.setDescripcion(estudio.getDescripcion());
            estudioOptional = this.save(estudioDb);
        }

        return Optional.ofNullable(estudioOptional);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }

    
}
