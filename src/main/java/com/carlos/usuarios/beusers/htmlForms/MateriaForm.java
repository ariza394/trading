package com.carlos.usuarios.beusers.htmlForms;

import org.springframework.web.multipart.MultipartFile;

import com.carlos.usuarios.beusers.models.entities.Materia;

public class MateriaForm extends Materia{

    private MultipartFile archivoAdjunto;
    
    public MultipartFile getArchivoAdjunto() {
        return archivoAdjunto;
    }
    public void setArchivoAdjunto(MultipartFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
 
}
