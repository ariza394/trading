package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.OrdenEstudio;
import com.carlos.usuarios.beusers.repositories.OrdenEstudioRepository;

@Service
public class OrdenEstudioServiceImpl implements OrdenEstudioService{



    @Autowired
    private OrdenEstudioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenEstudio> findAll() {
        return (List<OrdenEstudio>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenEstudio> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public OrdenEstudio save(OrdenEstudio orden) {
        System.out.println(orden.getNumero());
        return repository.save(orden);
    }

    @Override
    public Optional<OrdenEstudio> update(OrdenEstudio orden, Long id) {
        Optional<OrdenEstudio> o = repository.findById(id);
        OrdenEstudio ordenOptional = null;
        if(o.isPresent()){
            OrdenEstudio ordenDb = o.orElseThrow();
            ordenDb.setNumero(orden.getNumero());
            ordenOptional = this.save((ordenDb));
        }

        return Optional.ofNullable(ordenOptional);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
}
