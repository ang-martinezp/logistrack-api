CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    proveedor_id BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_pedido DATETIME,
    fecha_entrega_esperada DATETIME,
    observaciones VARCHAR(255)
);

CREATE TABLE detalles_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad DECIMAL (10,3) NOT NULL,
    precio_unitario DECIMAL (10,2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);
