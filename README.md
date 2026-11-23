# Registro de comandos - Proyecto JOKER

Este archivo recoge los comandos utilizados durante la preparación y comprobación del repositorio.
Se han ordenado según el proceso seguido en la conversación. Si se utilizaron otros comandos durante la actividad, deben añadirse en el lugar cronológico correspondiente antes de entregar.

## 1. Consultar y actualizar las ramas del repositorio

```cmd
git fetch origin
git switch main
git branch -a
git ls-tree --name-only origin/backend
git ls-tree --name-only origin/fronted
git pull --ff-only origin main
git diff --name-status main..origin/backend
git diff --name-status main..origin/fronted
```

## 2. Comprobar los archivos versionados

```cmd
git status
git pull origin main
git ls-files
dir
```

## 3. Comprobar Java y ejecutar la aplicación

```cmd
java -version
javac Calculadora.java
java Calculadora
```

## 4. Comprobar la aplicación en el navegador

Con el servidor Java iniciado, abrir esta dirección en Chrome:

http://localhost:8080

## Nota

El enunciado pide documentar **todos** los comandos utilizados y que el registro sea cronológico. Este documento recoge los comandos que constan en el proceso descrito, pero no puede garantizar que incluya comandos ejecutados que no se hayan registrado aquí. Añade cualquier otro comando que recuerdes haber utilizado.

La actividad también exige que el archivo se llame `READE.md`; por eso se conserva ese nombre, aunque normalmente se utiliza `README.md` para la documentación principal.
