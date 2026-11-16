const API_URL = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", () => {

    const pagina = document.body.dataset.page;

    // INICIAR SESIÓN
    if (pagina === "login") {

        const formulario = document.getElementById("form-login");

        formulario.addEventListener("submit", async (event) => {
            event.preventDefault();

            const nombre = document.getElementById("nombre").value;
            const contrasena = document.getElementById("contrasena").value;

            try {
                const respuesta = await fetch(API_URL + "/api/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        nombre: nombre,
                        contrasena: contrasena
                    })
                });

                const datos = await respuesta.json();

                if (datos.correcto) {
                    localStorage.setItem("usuario", datos.nombre);
                    window.location.href = "publicaciones.html";
                } else {
                    document.getElementById("mensaje").textContent =
                        datos.mensaje || "Usuario o contraseña incorrectos";
                }

            } catch (error) {
                document.getElementById("mensaje").textContent =
                    "No se pudo conectar con el servidor Java";
            }
        });
    }


    // COMPROBAR SESIÓN
    if (pagina === "perfil" || pagina === "publicaciones") {

        const usuario = localStorage.getItem("usuario");

        if (!usuario) {
            window.location.href = "index.html";
            return;
        }

        // CERRAR SESIÓN
        document.getElementById("cerrar-sesion").addEventListener("click", () => {
            localStorage.removeItem("usuario");
            window.location.href = "index.html";
        });
    }


    // CARGAR PERFIL
    if (pagina === "perfil") {

        const usuario = localStorage.getItem("usuario");

        async function cargarPerfil() {
            try {
                const respuesta = await fetch(API_URL + "/api/perfil/" + encodeURIComponent(usuario));

                if (!respuesta.ok) {
                    throw new Error("No se pudo cargar el perfil");
                }

                const datos = await respuesta.json();

                document.getElementById("nombre-perfil").textContent = datos.nombre;
                document.getElementById("descripcion").value = datos.descripcion || "";
                document.getElementById("foto").value = datos.foto || "";

                if (datos.foto) {
                    document.getElementById("foto-perfil").src = datos.foto;
                }

            } catch (error) {
                document.getElementById("mensaje").textContent =
                    "Error al cargar el perfil";
            }
        }

        cargarPerfil();


        // GUARDAR CAMBIOS DEL PERFIL
        document.getElementById("form-perfil").addEventListener("submit", async (event) => {
            event.preventDefault();

            const descripcion = document.getElementById("descripcion").value;
            const foto = document.getElementById("foto").value;

            try {
                const respuesta = await fetch(
                    API_URL + "/api/perfil/" + encodeURIComponent(usuario),
                    {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            descripcion: descripcion,
                            foto: foto
                        })
                    }
                );

                if (!respuesta.ok) {
                    throw new Error("No se pudo guardar");
                }

                document.getElementById("mensaje").textContent =
                    "Perfil actualizado correctamente";

                if (foto) {
                    document.getElementById("foto-perfil").src = foto;
                }

            } catch (error) {
                document.getElementById("mensaje").textContent =
                    "Error al guardar el perfil";
            }
        });
    }


    // PUBLICACIONES
    if (pagina === "publicaciones") {

        const usuario = localStorage.getItem("usuario");

        // CARGAR PUBLICACIONES
        async function cargarPublicaciones() {

            try {
                const respuesta = await fetch(API_URL + "/api/publicaciones");

                if (!respuesta.ok) {
                    throw new Error("No se pudieron cargar las publicaciones");
                }

                const publicaciones = await respuesta.json();

                const lista = document.getElementById("lista-publicaciones");

                lista.innerHTML = "";

                if (publicaciones.length === 0) {
                    lista.textContent = "Todavía no hay publicaciones";
                    return;
                }

                publicaciones.forEach(publicacion => {

                    const articulo = document.createElement("article");
                    articulo.className = "publicacion";

                    const titulo = document.createElement("h3");
                    titulo.textContent = publicacion.usuario;

                    const contenido = document.createElement("p");
                    contenido.textContent = publicacion.contenido;

                    articulo.appendChild(titulo);
                    articulo.appendChild(contenido);

                    lista.appendChild(articulo);
                });

            } catch (error) {
                document.getElementById("lista-publicaciones").textContent =
                    "Error al cargar las publicaciones";
            }
        }

        cargarPublicaciones();


        // CREAR PUBLICACIÓN
        document.getElementById("form-publicacion").addEventListener("submit", async (event) => {
            event.preventDefault();

            const contenido = document.getElementById("contenido").value;

            try {
                const respuesta = await fetch(API_URL + "/api/publicaciones", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        usuario: usuario,
                        contenido: contenido
                    })
                });

                if (!respuesta.ok) {
                    throw new Error("No se pudo publicar");
                }

                document.getElementById("contenido").value = "";
                document.getElementById("mensaje").textContent =
                    "¡Publicación creada!";

                cargarPublicaciones();

            } catch (error) {
                document.getElementById("mensaje").textContent =
                    "Error al crear la publicación";
            }
        });
    }

});
