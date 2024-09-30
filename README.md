
# API de Gestión de Clientes
Esta es una API RESTful para gestionar información de clientes. La API permite realizar operaciones CRUD 
(Crear, Leer, Actualizar, Eliminar) sobre los registros de clientes almacenados en una base de datos, utilizando MySQL y Spring Boot.

## Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL**
- **Hibernate**
- **Maven** para gestión de dependencias

## Requisitos previos
Para ejecutar esta aplicación, necesitarás:
- **Java 17** instalado.
- **MySQL** configurado y corriendo.
- Un entorno de desarrollo como **IntelliJ IDEA** o **Visual Studio Code**.
**NOTA:** Si va a utilizar Visual Studio Code asegurese de Instalar las extensiones necesarias
- **Java Extension Pack:**  Incluirá herramientas para compilar, ejecutar y depurar tu proyecto de Java.

- **Spring Boot Extension Pack:** Esta extensión incluye soporte para Spring Boot, lo que ayudará a manejar la aplicación de manera más efectiva.

- **Maven for Java:** Como el proyecto está basado en Maven, esta extensión permitirá ejecutar y gestionar las dependencias fácilmente.


## Instalacion y Configuracion
### 1. Clonar el Repositorio
Primero, clona el repositorio en tu máquina local:

   git clone https://github.com/javihermobiledev/clientes_api.git

### 2. Configuración del Proyecto
Dependencias y Configuración del Proyecto (pom.xml)
El archivo pom.xml es fundamental para la gestión de dependencias en un proyecto Maven.
   
### 2.1 Propiedades del Proyecto
Las versiones de dependencias y propiedades importantes se pueden manejar en esta sección:

    <properties>
      <java.version>17</java.version>
      <spring-boot.version>3.3.3</spring-boot.version> <!-- Tu versión original -->
      <mysql-connector.version>8.2.0</mysql-connector.version>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
      <maven.compiler.source>${java.version}</maven.compiler.source>
      <maven.compiler.target>${java.version}</maven.compiler.target>
    </properties>

A continuación se incluye un resumen de las dependencias más importantes usadas en este proyecto.
     
### 2.2 Dependencias Principales
El siguiente es un resumen de las principales dependencias usadas en el proyecto, incluidas en el archivo pom.xml:

    <dependencies>

    <!-- Dependencia de Spring Boot Starter Web -->
         <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
         </dependency>

    <!-- Dependencia de Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>

        <!-- Dependencia para MySQL -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>${mysql-connector.version}</version>
        </dependency>

        <!-- Dependencia de Spring Boot Starter Test para pruebas -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Dependencias para JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>

        <!-- Dependencias para Mockito -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>

     <dependency>
        <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-validation</artifactId>
       <version>3.3.4</version>
       </dependency>

     <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <scope>test</scope>
     </dependency>

     <dependency>
       <groupId>jakarta.persistence</groupId>
       <artifactId>jakarta.persistence-api</artifactId>
       <version>3.1.0</version>
     </dependency>

    </dependencies>

### 2.3. Configuración de Plugins
Los plugins son usados para compilar, empaquetar y ejecutar la aplicación.
Aquí tienes los más relevantes:

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>3.3.4</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <mainClass>com.ejemplo.clientesapi.ClientesApiApplication</mainClass>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <argLine>-Xshare:off</argLine>
                </configuration>
            </plugin>

        </plugins>
    </build>


### 3.Configuración de la Base de Datos
### 3.1. Crear la Base de Datos Manualmente (Desarrollo)
Crea una base de datos en MySQL para el entorno de desarrollo usando el siguiente comando SQL:

 ``` sql
     CREATE DATABASE cliente_db;
```
### 3.2 Configurar el Archivo application.properties con los detalles de la base de datos de desarrollo:
Edita el archivo src/main/resources/application.properties para configurar la conexión a tu base de datos:

### Configuración de la base de datos MYSQL
    spring.datasource.url=jdbc:mysql://localhost:3306/cliente_db?serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=12345
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### Inicialización de datos y creación automática de tablas
    spring.sql.init.mode=always
    spring.sql.init.schema-locations=classpath:scripts/create-tables.sql
    spring.sql.init.data-locations=classpath:scripts/insert-data.sql

### Configuración de JPA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    logging.level.org.springframework.orm.jpa=DEBUG
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

### 3.3. Crear la Base de Datos para Pruebas
Para el entorno de pruebas, se recomienda tener una base de datos separada. 
Puedes crear la base de datos para pruebas con el siguiente comando SQL:

 ``` sql
     CREATE DATABASE clientes_test;
```
### 3.4 Configurar el Archivo application-test.properties con los detalles de la base de datos de pruebas:
Edita el archivo src/test/resources/application-test.properties para configurar la conexión a tu base de datos:

### Configuración de la base de datos MYSQL  
    spring.datasource.url=jdbc:mysql://localhost:3306/clientes_test?serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=12345
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### Inicialización de datos y creación automática de tablas
    spring.sql.init.mode=always
    spring.sql.init.schema-locations=classpath:scripts/create-tables.sql
    spring.sql.init.data-locations=classpath:scripts/insert-data.sql

### Configuración de JPA
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    logging.level.org.springframework.orm.jpa=DEBUG
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

### 3.5 Archivos de Configuración
- application.properties: configuración para el entorno de desarrollo.
- application-test.properties: configuración para el entorno de pruebas, 
 que será automáticamente utilizada durante la ejecución de pruebas unitarias.

### 4. Script de Base de Datos Inicial (Este escript servira para las dos bases de datos)
A continuación, se proporciona un script SQL para crear la tabla cliente y agregar algunos registros iniciales. 
Guarda este script en un archivo llamado create-tables.sql y colócalo en la carpeta src/main/resources/scripts:

La aplicación está configurada para que las tablas se creen automáticamente al iniciar, y los datos iniciales se 
inserten automáticamente usando Hibernate y los scripts SQL proporcionados.
- Si prefieres crear las tablas y poblar los datos de forma manual, utiliza los siguientes scripts SQL:
 ``` 
-- Crear la tabla cliente

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(50),
    direccion TEXT,
    fecha_nacimiento DATE,
    fecha_registro DATETIME,
    activo BOOLEAN
);
```
A continuación, se proporciona un script SQL para agregar algunos registros iniciales. 
Guarda este script en un archivo llamado insert-data.sql y colócalo en la carpeta src/main/resources/scripts:
``` 
-- Insertar registros iniciales
 
INSERT INTO cliente (nombre, email, telefono, direccion, fecha_nacimiento, fecha_registro, activo) VALUES
('Javier Hernandez', 'javier.hernandez@example.com', '123-456-7890', 'Calle Falsa 123', '1985-10-15', '2024-09-16 12:00:00', true),
('Isabel Silvestre', 'isabel.silvestre@example.com', '123-456-7891', 'Avenida Siempre Viva 742', '1990-05-22', '2024-09-16 12:00:00', true),
('Alyssa Hernandez', 'alyssa.hernandez@example.com', '123-456-7892', 'Calle Mayor 456', '1995-08-10', '2024-09-16 12:00:00', true),
('Isabella Hernandez', 'isabella.hernandez@example.com', '123-456-7893', 'Calle Secundaria 789', '2000-03-05', '2024-09-16 12:00:00', true);

```
### 5. Ejecutar la Aplicación

Desde tu IDE, corre la clase principal:
    ClientesApiApplication.

O bien, desde la terminal:
    mvn spring-boot:run

Revisa los logs para asegurarte de que la aplicación se conecte correctamente a la base de datos y que las tablas y datos iniciales se creen automáticamente.

   La API estará disponible en http://localhost:8080/api/cliente

### 6. Pruebas
Ejecuta las pruebas unitarias con:
    mvn test

# Endpoints de la API
## 1. Obtener Todos los Clientes
URL: [/api/cliente] http://localhost:8080/api/cliente
Método HTTP: GET
Descripción: Obtiene la lista de todos los clientes registrados.
Respuesta: 200 OK con una lista de clientes en formato JSON.

- **Respuesta:**
 [
    {
        "id": 1,
        "nombre": "Javier Hernandez",
        "email": "javier.hernandez@example.com",
        "telefono": "123-456-7890",
        "direccion": "Calle Falsa 123",
        "fechaNacimiento": "1985-10-15",
        "fechaRegistro": "2024-09-16T07:00:00",
        "activo": true
    },
    {
        "id": 2,
        "nombre": "Isabel Silvestre",
        "email": "isabel.silvestre@example.com",
        "telefono": "123-456-7891",
        "direccion": "Avenida Siempre Viva 742",
        "fechaNacimiento": "1990-05-22",
        "fechaRegistro": "2024-09-16T07:00:00",
        "activo": true
    },
    {
        "id": 3,
        "nombre": "Alyssa Hernandez",
        "email": "alyssa.hernandez@example.com",
        "telefono": "123-456-7892",
        "direccion": "Calle Mayor 456",
        "fechaNacimiento": "1995-08-10",
        "fechaRegistro": "2024-09-16T07:00:00",
        "activo": true
    },
    {
        "id": 4,
        "nombre": "Isabella Hernandez",
        "email": "isabella.hernandez@example.com",
        "telefono": "123-456-7893",
        "direccion": "Calle Secundaria 789",
        "fechaNacimiento": "2000-03-05",
        "fechaRegistro": "2024-09-16T07:00:00",
        "activo": true
    }
]


## 2. Obtener un Cliente por ID
URL: [/api/cliente/{id}] http://localhost:8080/api/cliente/15
Método HTTP: GET
Descripción: Obtiene un cliente específico por su ID.
Parámetro: id (Long) - ID del cliente.
Respuesta: 200 OK con el cliente en formato JSON o 404 Not Found si el cliente no existe.

- **Respuesta:**
{
    "id": 15,
    "nombre": "Alyssa Hernandez",
    "email": "alyssa.hernandez@example.com",
    "telefono": "123-456-7892",
    "direccion": "Calle Mayor 456",
    "fechaNacimiento": "1995-08-10",
    "fechaRegistro": "2024-09-16T07:00:00",
    "activo": true
}


## 3. Crear un Nuevo Cliente
URL: [/api/cliente]  http://localhost:8080/api/cliente
Método HTTP: POST
Descripción: Crea un nuevo cliente.
Cuerpo de la Solicitud: Objeto JSON con los detalles del cliente.
Respuesta: 201 Created con el cliente creado en formato JSON.

 {
    "nombre": "Javier Hernandez",
    "email": "javihermobiledev@gmail.com",
    "telefono": "273-106-4388",
    "direccion": "Avenida 5 de mayo s/n",
    "fechaNacimiento": "1977-05-04",
    "fechaRegistro": "2024-09-16T12:12:00",
    "activo": true
}

- **Respuesta:**
{
    "id": 41,
    "nombre": "Javier Hernandez",
    "email": "javihermobiledev@gmail.com",
    "telefono": "273-106-4388",
    "direccion": "Avenida 5 de mayo s/n",
    "fechaNacimiento": "1977-05-04",
    "fechaRegistro": "2024-09-16T12:12:00",
    "activo": true
}



## 4. Actualizar un Cliente
URL: [/api/cliente/{id}]  http://localhost:8080/api/cliente/1
Método HTTP: PUT
Descripción: Actualiza los detalles de un cliente existente.
Parámetro: id (Long) - ID del cliente.
Cuerpo de la Solicitud: Objeto JSON con los detalles actualizados del cliente.
Respuesta: 200 OK con el cliente actualizado en formato JSON o 404 Not Found si el cliente no existe.

{
        "nombre": "Javier Hernandez",
        "email": "javier.hernandez@example.com",
        "telefono": "173-106-4388",
        "direccion": "Avenida 5 de mayo s/n",
        "fechaNacimiento": "1977-05-04",
        "fechaRegistro": "2024-09-16T07:00:00",
        "activo": true
    }

- **Respuesta:**
{
    "id": 1,
    "nombre": "Javier Hernandez",
    "email": "javier.hernandez@example.com",
    "telefono": "173-106-4388",
    "direccion": "Avenida 5 de mayo s/n",
    "fechaNacimiento": "1977-05-04",
    "fechaRegistro": "2024-09-30T00:28:46.7944361",
    "activo": true
}


## 5. Eliminar un Cliente
URL: [/api/cliente/{id}] http://localhost:8080/api/cliente/5
Método HTTP: DELETE
Descripción: Elimina un cliente específico por su ID.
Parámetro: id (Long) - ID del cliente.
Respuesta: 204 No Content si el cliente fue eliminado exitosamente o 404 Not Found si el cliente no existe.

- **Respuesta:**
   Cliente eliminado con exito

# Contribuciones
Si deseas contribuir al proyecto, por favor, realiza un fork del repositorio y envía un pull request con tus cambios. 
Asegúrate de seguir las convenciones de codificación y de incluir pruebas para cualquier nuevo código.

# Autor:
Desarrollado por: Javier Hernández Hernández

 


