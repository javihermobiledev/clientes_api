
# API de Gestión de Clientes
Esta es una API RESTful para gestionar información de clientes. La API permite realizar operaciones CRUD 
(Crear, Leer, Actualizar, Eliminar) sobre los registros de clientes almacenados en una base de datos, utilizando MySQL y Spring Boot.

# Requisitos
Java 17 o superior
Maven
MySQL (o una base de datos compatible)

# Configuración del Proyecto
    1. Clonar el Repositorio
  Primero, clona el repositorio en tu máquina local:

  git clone https://github.com/tuusuario/tu-repositorio.git
  cd tu-repositorio

    2. Configuración de la Base de Datos
   2.1 Crear la Base de Datos Manualmente
   Crea una base de datos en MySQL (o en el sistema de gestión de base de datos que estés usando). Puedes usar el siguiente comando SQL
   para crear la base de datos:
 ``` sql
     CREATE DATABASE clientes_db;
```
    2.2 Configurar el Archivo application.properties
   Edita el archivo src/main/resources/application.properties para configurar la conexión a tu base de datos:

# Configuración de la base de datos MYSQL
spring.datasource.url=jdbc:mysql://localhost:3306/clientes_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12345

# Inicialización de datos y creación automática de tablas
spring.datasource.initialization-mode=always
spring.datasource.schema=classpath:scripts/create-tables.sql
spring.datasource.data=classpath:scripts/insert-data.sql

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
logging.level.org.springframework.orm.jpa=DEBUG


    3. Script de Base de Datos Inicial
A continuación, se proporciona un script SQL para crear la tabla cliente y agregar algunos registros iniciales. 
Guarda este script en un archivo llamado schema.sql y colócalo en la carpeta src/main/resources:

La aplicación está configurada para que las tablas se creen automáticamente al iniciar, y los datos iniciales se 
inserten automáticamente usando Hibernate y los scripts SQL proporcionados.
- Si prefieres crear las tablas y poblar los datos de forma manual, utiliza los siguientes scripts SQL:
 ``` 
-- Crear la tabla cliente

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(50),
    direccion TEXT,
    fechaNacimiento DATE,
    fechaRegistro DATETIME,
    activo BOOLEAN
);


-- Insertar registros iniciales

INSERT INTO cliente (nombre, email, telefono, direccion, fechaNacimiento, fechaRegistro, activo) VALUES
('Javier Hernandez', 'javier@example.com', '555-1234', 'Calle Falsa 123', '1985-06-15', '2024-09-18 10:00:00', TRUE),
('Isabel Silvestre', 'isabel@example.com', '555-5678', 'Avenida Siempre Viva 456', '1990-07-22', '2024-09-18 11:00:00', TRUE),
('Alyssa Hernandez', 'alyssa@example.com', '555-8765', 'Calle del Sol 789', '1995-08-30', '2024-09-18 12:00:00', FALSE),
('Isabella Hernandez', 'isabella@example.com', '555-4321', 'Calle Luna 101', '1998-09-12', '2024-09-18 13:00:00', TRUE);
```
    4. Ejecutar la Aplicación
   Para ejecutar la aplicación, utiliza el siguiente comando Maven:

mvn spring-boot:run

Revisa los logs para asegurarte de que la aplicación se conecte correctamente a la base de datos y que las tablas y datos iniciales se creen automáticamente.

La API estará disponible en http://localhost:8080/api/clientes.

# Endpoints de la API
    1. Obtener Todos los Clientes
   URL: /api/clientes
   Método: GET
   Descripción: Obtiene la lista de todos los clientes.
   Respuesta: 200 OK con una lista de clientes en formato JSON.

    2. Obtener un Cliente por ID
   URL: /api/clientes/{id}
   Método: GET
   Descripción: Obtiene un cliente específico por su ID.
   Parámetro: id (Long) - ID del cliente.
   Respuesta: 200 OK con el cliente en formato JSON o 404 Not Found si el cliente no existe.

    3. Crear un Nuevo Cliente
   URL: /api/clientes
   Método: POST
   Descripción: Crea un nuevo cliente.
   Cuerpo de la Solicitud: Objeto JSON con los detalles del cliente.
   Respuesta: 201 Created con el cliente creado en formato JSON.


    4. Actualizar un Cliente
   URL: /api/clientes/{id}
   Método: PUT
   Descripción: Actualiza los detalles de un cliente existente.
   Parámetro: id (Long) - ID del cliente.
   Cuerpo de la Solicitud: Objeto JSON con los detalles actualizados del cliente.
   Respuesta: 200 OK con el cliente actualizado en formato JSON o 404 Not Found si el cliente no existe.

    5. Eliminar un Cliente
   URL: /api/clientes/{id}
   Método: DELETE
   Descripción: Elimina un cliente específico por su ID.
   Parámetro: id (Long) - ID del cliente.
   Respuesta: 204 No Content si el cliente fue eliminado exitosamente o 404 Not Found si el cliente no existe.

# Contribuciones
Si deseas contribuir al proyecto, por favor, realiza un fork del repositorio y envía un pull request con tus cambios. 
Asegúrate de seguir las convenciones de codificación y de incluir pruebas para cualquier nuevo código.

# Licencia
Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.


 


