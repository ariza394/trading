package com.carlos.usuarios.beusers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
