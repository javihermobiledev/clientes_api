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
