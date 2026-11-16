package com.redsocial;

public class Usuario {

    private String nombre;
    private String contrasena;
    private String foto;
    private String descripcion;

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.foto = "default.png";
        this.descripcion = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getFoto() {
        return foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
