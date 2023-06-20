package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.Materia;

public interface MateriaService {

    List<Materia> findAll();
    
    Optional<Materia> findById(Long id);

    Materia save(Materia materia);

    Optional<Materia> update(Materia materia, Long id);

    void remove(Long id); 
}
