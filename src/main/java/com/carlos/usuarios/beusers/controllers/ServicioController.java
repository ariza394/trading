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

import com.carlos.usuarios.beusers.models.entities.Servicio;
import com.carlos.usuarios.beusers.services.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    
    @Autowired
    private ServicioService service;

    @GetMapping
    public List<Servicio> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Servicio> servicio = service.findById(id);

        if(servicio.isPresent()){
            return ResponseEntity.ok(servicio.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Servicio servicio){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Servicio servicio, @PathVariable Long id){
        Optional<Servicio> o = service.update(servicio, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Servicio> o = service.findById(id);
        if(o.isPresent()){
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
