package com.carlos.usuarios.beusers.controllers;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.carlos.usuarios.beusers.htmlForms.AnexosForm;
import com.carlos.usuarios.beusers.models.entities.AnexoMaterial;
import com.carlos.usuarios.beusers.services.AnexoMateriaService;

@RestController
@RequestMapping("/anexo/material")
public class AnexoMaterialController {
    
    @Autowired
    private AnexoMateriaService service;

    @GetMapping
    public List<AnexoMaterial> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<AnexoMaterial> anexoOptional = service.findById(id);

        if(anexoOptional.isPresent()){
            return ResponseEntity.ok(anexoOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute AnexosForm form){

        AnexoMaterial anexoMaterial = new AnexoMaterial();
        anexoMaterial.setDescripcion(form.getDescripcion());
        anexoMaterial.setNombre(form.getNombre());
        anexoMaterial.setTipo(form.getTipo());
        anexoMaterial.setMateria(form.getMateria());
        

        if(form.getArchivoAdjunto() != null){
            try {
                MultipartFile adjunto = form.getArchivoAdjunto();
                String fileName = adjunto.getOriginalFilename();
                String filePath = "C:\\Users\\Carlos\\Documents\\trading\\be\\be-users\\src\\archivos\\" + fileName;

                // Guardar el archivo en el servidor
                byte[] bytes = adjunto.getBytes();
                FileCopyUtils.copy(bytes, new File(filePath));
                anexoMaterial.setImagen(filePath);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(anexoMaterial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@ModelAttribute AnexosForm form, @PathVariable Long id){
        
        Optional<AnexoMaterial> anexoOptional = service.findById(id);
        if (anexoOptional.isPresent()) {
            AnexoMaterial anexoMaterial = anexoOptional.get();
            anexoMaterial.setDescripcion(form.getDescripcion());
            anexoMaterial.setNombre(form.getNombre());
            anexoMaterial.setTipo(form.getTipo());
            anexoMaterial.setMateria(form.getMateria());

            if(form.getArchivoAdjunto() != null){
                try {
                    MultipartFile adjunto = form.getArchivoAdjunto();
                    String originalFileName = adjunto.getOriginalFilename();
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                    String fileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                    String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
                    String newFileName = fileName + "-" + formattedDateTime + fileExtension;
                    String filePath = "C:\\Users\\Carlos\\Documents\\trading\\be\\be-users\\src\\archivos\\" + newFileName;

                    // Eliminar archivo anterior, si existe
                    if (anexoMaterial.getImagen() != null) {
                        File previousFile = new File(anexoMaterial.getImagen());
                        if (previousFile.exists()) {
                            previousFile.delete();
                        }
                    }
                    // Guardar el archivo en el servidor
                    byte[] bytes = adjunto.getBytes();
                    FileCopyUtils.copy(bytes, new File(filePath));
                    anexoMaterial.setImagen(filePath);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
            // Resto del código de actualización del anexoMaterial
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(anexoMaterial));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<AnexoMaterial> o = service.findById(id);
        if(o.isPresent()){
            AnexoMaterial anexoMaterial = o.get();

            // Eliminar archivo anterior, si existe
            if (anexoMaterial.getImagen() != null) {
                File previousFile = new File(anexoMaterial.getImagen());
                if (previousFile.exists()) {
                    previousFile.delete();
                }
            }
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
