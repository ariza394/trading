package com.carlos.usuarios.beusers.controllers;

import java.io.Serial;
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

import com.carlos.usuarios.beusers.models.entities.Materia;
import com.carlos.usuarios.beusers.models.entities.OrdenEstudio;
import com.carlos.usuarios.beusers.models.entities.PlanEstudio;
import com.carlos.usuarios.beusers.models.entities.User;
import com.carlos.usuarios.beusers.services.MateriaService;
import com.carlos.usuarios.beusers.services.OrdenEstudioService;
import com.carlos.usuarios.beusers.services.PlanEstudioService;

@RestController
@RequestMapping("/orden/estudio")
public class OrdenEstudioController {
    
    @Autowired
    private OrdenEstudioService service;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private PlanEstudioService planEstudioService;

    @GetMapping
    public List<OrdenEstudio> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<OrdenEstudio> orderdenOptional = service.findById(id);

        if(orderdenOptional.isPresent()){
            OrdenEstudio ordenEstudio = orderdenOptional.get();
            Materia materia = ordenEstudio.getMateria();
            PlanEstudio estudio = ordenEstudio.getEstudio();

            Materia materiaEncontrada = materiaService.findById(materia.getId()).orElse(null);
            PlanEstudio estudioEncontrado = planEstudioService.findById(estudio.getId()).orElse(null);
            
            ordenEstudio.setMateria(materiaEncontrada);
            ordenEstudio.setEstudio(estudioEncontrado);

            return ResponseEntity.ok(ordenEstudio);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrdenEstudio ordenEstudio){
        OrdenEstudio ordenEstudioDb = service.save(ordenEstudio);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenEstudioDb.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrdenEstudio ordenEstudio, @PathVariable Long id){
        Optional<OrdenEstudio> o = service.update(ordenEstudio, id);
        if (o.isPresent()){ 
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<OrdenEstudio> o = service.findById(id);
        if(o.isPresent()){
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
