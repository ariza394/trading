package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.Servicio;

public interface ServicioService {
    List<Servicio> findAll();

    Optional<Servicio> findById(Long id);

    Servicio save(Servicio servicio);

    Optional<Servicio> update(Servicio servicio, Long id);

    void remove(Long id); 
}
