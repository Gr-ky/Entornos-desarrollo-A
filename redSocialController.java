package com.redsocial;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RedSocialController {

    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Publicacion> publicaciones = new ArrayList<>();

    public RedSocialController() {
        usuarios.put("rochii", new Usuario("rochii", "1234"));
        usuarios.put("usuario2", new Usuario("usuario2", "5678"));
    }

    // INICIAR SESION
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest datos) {

        Map<String, Object> respuesta = new HashMap<>();

        Usuario usuario = usuarios.get(datos.getNombre());

        if (usuario != null &&
                usuario.getContrasena().equals(datos.getContrasena())) {

            respuesta.put("correcto", true);
            respuesta.put("mensaje", "Inicio de sesión correcto");
            respuesta.put("nombre", usuario.getNombre());
            respuesta.put("foto", usuario.getFoto());
            respuesta.put("descripcion", usuario.getDescripcion());

        } else {

            respuesta.put("correcto", false);
            respuesta.put("mensaje", "Usuario o contraseña incorrectos");
        }

        return respuesta;
    }

    // OBTENER PERFIL
    @GetMapping("/perfil/{nombre}")
    public Usuario obtenerPerfil(@PathVariable String nombre) {

        return usuarios.get(nombre);
    }

    // EDITAR PERFIL
    @PutMapping("/perfil/{nombre}")
    public String editarPerfil(
            @PathVariable String nombre,
            @RequestBody PerfilRequest datos) {

        Usuario usuario = usuarios.get(nombre);

        if (usuario == null) {
            return "Usuario no encontrado";
        }

        usuario.setDescripcion(datos.getDescripcion());
        usuario.setFoto(datos.getFoto());

        return "Perfil actualizado correctamente";
    }

    // CREAR PUBLICACION
    @PostMapping("/publicaciones")
    public String crearPublicacion(@RequestBody PublicacionRequest datos) {

        if (!usuarios.containsKey(datos.getUsuario())) {
            return "Usuario no encontrado";
        }

        Publicacion publicacion = new Publicacion(
                datos.getUsuario(),
                datos.getContenido()
        );

        publicaciones.add(publicacion);

        return "Publicación creada correctamente";
    }

    // OBTENER PUBLICACIONES
    @GetMapping("/publicaciones")
    public List<Publicacion> obtenerPublicaciones() {

        return publicaciones;
    }

    // DATOS DEL LOGIN
    public static class LoginRequest {

        private String nombre;
        private String contrasena;

        public String getNombre() {
            return nombre;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }

    // DATOS PARA EDITAR PERFIL
    public static class PerfilRequest {

        private String descripcion;
        private String foto;

        public String getDescripcion() {
            return descripcion;
        }

        public String getFoto() {
            return foto;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
    }

    // DATOS PARA CREAR PUBLICACION
    public static class PublicacionRequest {

        private String usuario;
        private String contenido;

        public String getUsuario() {
            return usuario;
        }

        public String getContenido() {
            return contenido;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public void setContenido(String contenido) {
            this.contenido = contenido;
        }
    }
}
