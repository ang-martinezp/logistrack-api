CREATE TABLE despachos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_despacho DATETIME,
    fecha_entrega DATETIME,
    direccion_destino VARCHAR(255),
    transportista VARCHAR(100)
);

CREATE TABLE detalles_despacho (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    despacho_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad DECIMAL (10,3) NOT NULL,
    CONSTRAINT fk_despacho FOREIGN KEY (despacho_id) REFERENCES despachos(id)
);