package com.carlos.usuarios.beusers.services;

import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.models.entities.AnexoMaterial;

public interface AnexoMateriaService {
    List<AnexoMaterial> findAll();
    
    Optional<AnexoMaterial> findById(Long id);

    AnexoMaterial save(AnexoMaterial anexoMaterial);

    Optional<AnexoMaterial> update(AnexoMaterial anexoMaterial, Long id);

    void remove(Long id); 
}
