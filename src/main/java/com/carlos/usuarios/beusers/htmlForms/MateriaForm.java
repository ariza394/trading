package com.carlos.usuarios.beusers.htmlForms;

import org.springframework.web.multipart.MultipartFile;

public class MateriaForm {
    private String nombre;
    private String imagen;
    private String descripcion;
    private MultipartFile archivoAdjunto;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public MultipartFile getArchivoAdjunto() {
        return archivoAdjunto;
    }
    public void setArchivoAdjunto(MultipartFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    
    
    
}
