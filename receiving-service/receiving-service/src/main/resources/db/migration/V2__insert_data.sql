INSERT INTO recepciones (pedido_id, proveedor_id, estado, fecha_recepcion, observaciones) VALUES
  (1, 1, 'COMPLETA', NOW(), 'Recepción sin novedades'),
  (2, 2, 'PARCIAL', NOW(), 'Faltaron 3 unidades del producto 2'),
  (3, 1, 'PENDIENTE', NULL, 'En espera de llegada');

INSERT INTO detalles_recepcion (recepcion_id, producto_id, cantidad_esperada, cantidad_recibida) VALUES
 (1, 1, 10.000, 10.000),
 (1, 2, 5.000, 5.000),
 (2, 3, 8.000, 5.000),
 (3, 1, 6.000, 0.000);