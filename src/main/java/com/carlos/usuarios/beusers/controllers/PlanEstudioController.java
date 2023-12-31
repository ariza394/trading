package com.carlos.usuarios.beusers.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.usuarios.beusers.models.entities.PlanEstudio;
import com.carlos.usuarios.beusers.services.PlanEstudioService;

@RestController
@RequestMapping("/plan/estudio")
public class PlanEstudioController {
    
    @Autowired
    private PlanEstudioService service;

    @GetMapping
    public List<PlanEstudio> list(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PlanEstudio estudio){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudio));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PlanEstudio estudio, @PathVariable Long id){
        Optional<PlanEstudio> o = service.update(estudio, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<PlanEstudio> o = service.findById(id);
        if(o.isPresent()){
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
