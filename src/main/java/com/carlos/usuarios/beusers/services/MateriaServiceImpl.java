package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.Materia;
import com.carlos.usuarios.beusers.repositories.MateriaRepository;

@Service
public class MateriaServiceImpl implements MateriaService{

    @Autowired
    private MateriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Materia> findAll(){
        return (List<Materia>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Materia> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Materia save(Materia materia) {
        return repository.save(materia);
    }

    @Override
    @Transactional
    public Optional<Materia> update(Materia materia, Long id) {
        Optional<Materia> o = this.findById(id);
        Materia materiaOptional = null;
        if(o.isPresent()){
            Materia materiaDb = o.orElseThrow();
            materiaDb.setNombre(materia.getNombre());
            materiaDb.setDescripcion(materia.getDescripcion());
            materiaDb.setImagen(materia.getImagen());
            materiaOptional = this.save(materiaDb);
        }

        return Optional.ofNullable(materiaOptional);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
}
