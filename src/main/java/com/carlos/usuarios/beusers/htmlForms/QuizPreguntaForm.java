package com.carlos.usuarios.beusers.htmlForms;

import org.springframework.web.multipart.MultipartFile;

import com.carlos.usuarios.beusers.models.entities.QuizPregunta;

public class QuizPreguntaForm extends QuizPregunta{
    
    private MultipartFile archivoAdjunto;
    
    public MultipartFile getArchivoAdjunto() {
        return archivoAdjunto;
    }
    public void setArchivoAdjunto(MultipartFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
    
}
