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

import com.carlos.usuarios.beusers.models.entities.ServicioTomado;
import com.carlos.usuarios.beusers.services.ServicioTomadoService;

@RestController
@RequestMapping("/servicio/tomado")
public class ServicioTomadoController {
    @Autowired
    private ServicioTomadoService service;

    @GetMapping
    public List<ServicioTomado> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<ServicioTomado> servicio = service.findById(id);

        if(servicio.isPresent()){
            return ResponseEntity.ok(servicio.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ServicioTomado servicioTomado){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(servicioTomado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ServicioTomado servicioTomado, @PathVariable Long id){
        Optional<ServicioTomado> o = service.update(servicioTomado, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<ServicioTomado> o = service.findById(id);
        if(o.isPresent()){
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
