package com.carlos.usuarios.beusers.controllers;

import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carlos.usuarios.beusers.clases.FileUtil;
import com.carlos.usuarios.beusers.htmlForms.MateriaForm;
import com.carlos.usuarios.beusers.models.entities.Materia;
import com.carlos.usuarios.beusers.models.entities.User;
import com.carlos.usuarios.beusers.services.MateriaService;
import com.carlos.usuarios.beusers.services.StorageService;

@RestController
@RequestMapping("/materia")
public class MateriaController {
    
    @Autowired
    private MateriaService service;

    @Autowired
    private StorageService serviceStorage;

    @GetMapping
    public List<Materia> list(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute MateriaForm form){
        Materia materia = new Materia();
        materia.setNombre(form.getNombre());
        materia.setDescripcion(form.getDescripcion());
        
        if (form.getArchivoAdjunto() != null) {
            try {
                String filePath = serviceStorage.uploadFile(form.getArchivoAdjunto(), "materia3");
                materia.setImagen(filePath);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(materia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute MateriaForm form,@PathVariable Long id){
        Optional<Materia> existingMateria = service.findById(id);

        if (!existingMateria.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Materia materia = existingMateria.get();
        materia.setNombre(form.getNombre());
        materia.setDescripcion(form.getDescripcion());

        if (form.getArchivoAdjunto() != null) {
            try {
                // Nombre imagen anterior
                String imagenAnterior = materia.getImagen();
                String filePath = serviceStorage.updateFile(form.getArchivoAdjunto(), "materia3", imagenAnterior);
                materia.setImagen(filePath);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
        Optional<Materia> o = service.update(materia,id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
