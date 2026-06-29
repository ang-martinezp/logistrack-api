CREATE TABLE notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    destinatario VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL
);
