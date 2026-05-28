CREATE TABLE stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producto_id BIGINT NOT NULL UNIQUE,
    cantidad_actual DECIMAL (10,3) NOT NULL DEFAULT 0,
    cantidad_minima DECIMAL (10,3) NOT NULL DEFAULT 0,
    ultima_actualizacion DATETIME
);

CREATE TABLE movimientos_inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producto_id BIGINT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    cantidad DECIMAL(10,3) NOT NULL,
    motivo VARCHAR(255),
    fecha_movimiento DATETIME
);

CREATE TABLE alertas_stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producto_id BIGINT NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_creacion DATETIME,
    fecha_resolucion DATETIME
);
