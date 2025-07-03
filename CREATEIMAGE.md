# 📦 Crear Imagen y Contenedor con Docker

Este documento describe los pasos necesarios para construir una imagen Docker 
a partir de un `BuilPack` y ejecutar un contenedor en modo *detached* (`-d`),
exponiendo el puerto `8080` en Spring Boot 2.3+.

---

## 📁 Prerrequisitos

- Tener Docker instalado y funcionando en tu máquina.
- Tener un proyecto spring boot con el plugin spring-boot-maven-plugin(agregado por default).
- Tener packaging en jar

---

## 🛠️ Paso 1: Definir el nombre de la imagen a crear
Dentro del plugin spring-boot-maven-plugin definir el nombre de la 
imagen a crear con el tag image
```xml
<configuration>
					<image>
						<name>albertoesteva88/${project.artifactId}:s4</name>

					</image>
                    .
                    .
                    .
<configuration>
```

--
## 🔨 Paso 2: Construir la imagen Docker
Ejecutar en la terminal a nivel de la raiz del proyecto
mvn spring-boot:build-image

Esto generará una imagen del proyecto ademas de descargar 
otras que serviran de apoyo en la creacion de la imagen.
La imagen se genera con tiempo de creacion de 45 años, 
no es un error es una caracteristica de `BuilPack`.

--
## 🚀 Paso 3: Crear y ejecutar el contenedor
docker run -d -p 8090:8090 albertoesteva88/loans:s4

Al ejecutar esta instruccion si se cambia el puerto crea un 
contenedor nuevo con un nombre diferente generado automaticamente

si se agrega el tag name se puede poner un nombre personalizado

docker run -d -p 8090:8090 --name loans albertoesteva88/loans:s4

