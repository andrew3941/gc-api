# Preving Intranet RESTFul Api

Proyecto que ofrece API REST para los nuevos proyectos de la intranet.

## Documentacion

Se puede acceder a la documentacion elaborado con Swagger desde la siguiente url:

[Documentacion Swagger Intranet REST Api](https://localhost:8443/intranet-api/swagger-ui.html)

## Dependencias

* Java 8
* Spring Boot
* Swagger. Libreria utilizada para documentar el API REST.
* Maven. Se necesitan Maven para añadir al repositorio local de maven el driver jdbc de Oracle.


## Ejecución del Proyecto

### Configurar Maven en el Equipo

Antes de correr el proyecto necesitamos añadir al repositorio local de Maven el driver JDBC de Oracle.

Si no tienes instalado maven tan solo debes seguir los siguientes sencillos pasos:

1. Descargar maven de la siguiente url: [Download Maven](https://maven.apache.org/download.cgi?Preferred=ftp%3A%2F%2Fmirror.reverse.net%2Fpub%2Fapache%2F)
2. Descomprimir maven en una carpeta de tu equipo por ejemplo /dev/lib/maven
3. Añadir a la variable PATH el directorio bin del home de maven. Puedes consultar como realizar esto desde la siguiente direccion: (https://maven.apache.org/install.html)
4. Confirmar que tenemos maven instalado:

```
$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T17:41:47+01:00)
Maven home: /home/javier-montesinos/dev/bin/apache-maven-3.3.9
Java version: 1.8.0_121, vendor: Oracle Corporation
Java home: /usr/lib/jvm/java-8-oracle/jre
Default locale: es_ES, platform encoding: UTF-8
OS name: "linux", version: "4.4.0-64-generic", arch: "amd64", family: "unix"
```
### Añadir driver JDBC al repositorio local de maven

Nos descargaremos el driver ojbc6 que es el driver compatible con la version del Oracle del proyecto: Oracle Database 11g (11.2.0.4.0 -	64bit) 
del siguiente enlace: [Descargar ojdbc6 deOracle](http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html)

Una vez descargado lo añadiremos al proyecto:

```
mvn install:install-file -Dfile=ojdbc6.jar 
    -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 
    -Dpackaging=jar
```

### Correr el proyecto

Una vez hayamos actualizado las dependencias gradle del proyecto, podemos correr el proyecto de dos formas diferentes:
 
1. Desde Spring Boot, para esto tan solo deberemos lanzar el proyecto para ejecutarlo

```
./gradlew bootRun
```

2. Por otro lado podemos querer generar un fichero war para desplegar por ejemplo sobre en un tomcat externo.

```
./gradlew war
```

Este comando generar en la carpeta *build/libs* un fichero war que podremos desplegar en el tomcat de destino.

### Funcionalidad ya contemplada

* Uso de perfiles de desarrollo y produccion.
* Uso de conexines a Oracle sin utilizan JNDI para dev
* Añadir SSL [Uso de SSL en Spring Boot](https://spring.io/guides/tutorials/bookmarks/)
* Configuracion para usar conexiones a Oracle que utilicen JNDI
* Documentar correctamente dominio y servicios REST con Swagger
* Desplegar proyecto en un tomcat con java 8
* Añadir seguridad basada en JWT. Usar proyecto de referencia en 
[Perfil de Javier Montesinos de Github](https://github.com/fjmontesinos/spring-jjwt) 


### Cuestiones que se incluiran en la siguiente Version

* Uso de JPA. Utilizado en la parte de Seguridad