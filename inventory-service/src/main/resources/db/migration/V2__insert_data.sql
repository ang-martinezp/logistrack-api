INSERT INTO stock (producto_id, cantidad_actual, cantidad_minima, ultima_actualizacion) VALUES
    (1, 15.000, 5.000, NOW()),
    (2, 30.000, 10.000, NOW()),
    (3, 50.000, 20.000, NOW()),
    (4, 8.000, 5.000, NOW()),
    (5, 3.000, 10.000, NOW());

INSERT INTO movimientos_inventario ( producto_id, tipo, cantidad, motivo, fecha_movimiento) VALUES
    (1, 'ENTRADA', 15.000, 'Compra inicial de laptops', NOW()),
    (2, 'ENTRADA', 30.000, 'Compra inicial de teclados', NOW()),
    (3, 'ENTRADA', 50.000, 'Compra inicial de pasta termica', NOW()),
    (4, 'ENTRADA', 10.000, 'Compra inicial de papel A4', NOW()),
    (4, 'SALIDA', 2.000, 'Despacho a oficina central', NOW()),
    (5, 'ENTRADA', 3.000, 'Compra inicial de liquido refrigerante', NOW());

INSERT INTO alertas_stock (producto_id, mensaje, estado, fecha_creacion) VALUES
    (5, 'Stock bajo para producto id: 5. Actual: 3.000 Minimo: 10.000', 'ACTIVA', NOW());