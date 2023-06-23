package com.carlos.usuarios.beusers.htmlForms;

import org.springframework.web.multipart.MultipartFile;

import com.carlos.usuarios.beusers.models.entities.Materia;

public class AnexosForm {
    private String nombre;

    private String descripcion;

    private String tipo;

    private MultipartFile archivoAdjunto;

    private Materia materia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public MultipartFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(MultipartFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
}
