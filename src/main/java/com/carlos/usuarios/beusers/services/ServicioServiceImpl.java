package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.Servicio;
import com.carlos.usuarios.beusers.repositories.ServicioRepository;

@Service
public class ServicioServiceImpl implements ServicioService{
    
    @Autowired
    private ServicioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> findAll() {
        return (List<Servicio>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Servicio save(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    @Transactional
    public Optional<Servicio> update(Servicio servicio, Long id) {
        Optional<Servicio> o = this.findById(id);
        Servicio servicioOptional = null;
        if(o.isPresent()){
            Servicio servicioDb = o.orElseThrow();
            servicioDb.setNombre(servicio.getNombre());
            servicioDb.setDescripcion(servicio.getDescripcion());
            servicioOptional = this.save(servicioDb);
        }

        return Optional.ofNullable(servicioOptional);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
