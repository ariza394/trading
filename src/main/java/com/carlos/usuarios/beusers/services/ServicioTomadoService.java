package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carlos.usuarios.beusers.models.entities.ServicioTomado;

public interface ServicioTomadoService {
    List<ServicioTomado> findAll();

    Optional<ServicioTomado> findById(Long id);

    ServicioTomado save(ServicioTomado servicioTomado);

    Optional<ServicioTomado> update(ServicioTomado servicioTomado, Long id);

    void remove(Long id); 
}
