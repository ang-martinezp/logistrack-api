CREATE TABLE reportes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    fecha_generacion DATETIME NOT NULL,
    contenido TEXT NOT NULL
);
