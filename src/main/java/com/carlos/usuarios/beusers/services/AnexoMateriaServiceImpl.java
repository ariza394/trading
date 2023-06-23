package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.usuarios.beusers.models.entities.AnexoMaterial;
import com.carlos.usuarios.beusers.repositories.AnexoMateriaRepository;

@Service
public class AnexoMateriaServiceImpl implements AnexoMateriaService{
    
    @Autowired
    private AnexoMateriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<AnexoMaterial> findAll(){
        return (List<AnexoMaterial>) repository.findAll();
    }

    @Override
    public Optional<AnexoMaterial> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public AnexoMaterial save(AnexoMaterial anexoMaterial) {
        return repository.save(anexoMaterial);
    }

    @Override
    public Optional<AnexoMaterial> update(AnexoMaterial anexoMaterial, Long id) {
        Optional<AnexoMaterial> o = this.findById(id);
        AnexoMaterial anexoOptional = null;
        if(o.isPresent()){
            AnexoMaterial anexoDb = o.orElseThrow();
            anexoDb.setDescripcion(anexoMaterial.getDescripcion());
            anexoDb.setImagen(anexoMaterial.getImagen());
            anexoDb.setNombre(anexoMaterial.getNombre());
            anexoDb.setTipo(anexoMaterial.getTipo());
            anexoDb.setMateria(anexoMaterial.getMateria());
            anexoOptional = this.save(anexoDb);
        }
        return Optional.ofNullable(anexoOptional);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
}
