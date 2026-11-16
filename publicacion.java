package com.redsocial;

public class Publicacion {

    private String usuario;
    private String contenido;

    public Publicacion(String usuario, String contenido) {
        this.usuario = usuario;
        this.contenido = contenido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContenido() {
        return contenido;
    }
}
