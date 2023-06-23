package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.OrdenEstudio;

public interface OrdenEstudioService {
    List<OrdenEstudio> findAll();
    
    Optional<OrdenEstudio> findById(Long id);

    OrdenEstudio save(OrdenEstudio orden);

    Optional<OrdenEstudio> update(OrdenEstudio orden, Long id);

    void remove(Long id); 
}
