CREATE TABLE recepciones (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             pedido_id BIGINT NOT NULL,
                             proveedor_id BIGINT NOT NULL,
                             estado VARCHAR(20) NOT NULL,
                             fecha_recepcion DATETIME,
                             observaciones VARCHAR(500)
);

CREATE TABLE detalles_recepcion (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    recepcion_id BIGINT NOT NULL,
                                    producto_id BIGINT NOT NULL,
                                    cantidad_esperada DECIMAL(10,3) NOT NULL,
                                    cantidad_recibida DECIMAL(10,3) NOT NULL DEFAULT 0,
                                    CONSTRAINT fk_recepcion FOREIGN KEY (recepcion_id) REFERENCES recepciones(id)
);