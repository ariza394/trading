package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.PlanEstudio;

public interface PlanEstudioService {
    
    List<PlanEstudio> findAll();

    Optional<PlanEstudio> findById(Long id);

    PlanEstudio save(PlanEstudio estudio);

    Optional<PlanEstudio> update(PlanEstudio estudio, Long id);

    void remove(Long id); 
}
