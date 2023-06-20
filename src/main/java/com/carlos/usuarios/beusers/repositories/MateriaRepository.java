package com.carlos.usuarios.beusers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carlos.usuarios.beusers.models.entities.Materia;

public interface MateriaRepository extends CrudRepository<Materia, Long>{
    
}
