# Calculadora Java + HTML

Suma, resta, multiplicación y potencias con enteros de hasta 5 dígitos.

## Comandos utilizados (orden cronológico)

1. Crear el repositorio en GitHub (web): New repository, público, con README.
2. `git clone https://github.com/Gr-ky/Entornos-desarrollo-A.git`: descarga el repo.
3. `cd Entornos-desarrollo-A`: entra a la carpeta.
4. `git checkout -b frontend`: crea la rama frontend y se pasa a ella.
5. `git add index.html`: prepara el archivo HTML.
6. `git status`: comprueba qué se va a commitear.
7. `git commit -m "Agrego formulario HTML de la calculadora"`: guarda el cambio.
8. `git push -u origin frontend`: publica la rama en GitHub.
9. `git checkout main`: vuelve a main.
10. `git checkout -b backend`: crea la rama backend desde main.
11. `git add Calculadora.java`, `git status`, `git commit -m "Agrego logica Java de la calculadora"`, `git push -u origin backend`
12. `git checkout main`
13. `git merge --no-ff frontend -m "Merge de frontend en main"`
14. `git merge --no-ff backend -m "Merge de backend en main"`
15. `git push origin main`
16. `git add README.md`, `git commit -m "Actualizo comentarios del README"`, `git push origin main`

## Cómo ejecutar

    javac Calculadora.java
    java Calculadora

Abrir http://localhost:8080
