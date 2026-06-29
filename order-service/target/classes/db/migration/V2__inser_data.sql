INSERT INTO pedidos (proveedor_id, estado, fecha_pedido, fecha_entrega_esperada, observaciones) VALUES
    (1, 'CONFIRMADO', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'Pedido inicial de equipos'),
    (2, 'PENDIENTE', NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 'Pedido de insumos de oficina'),
    (1, 'ENTREGADO', DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY), 'Pedido anterior entregado');

INSERT INTO detalles_pedido ( pedido_id, producto_id, cantidad, precio_unitario) VALUES
     (1, 1, 5.000, 850000.00),
     (1, 2, 10.000, 25000.00),
     (2, 4, 20.000, 8500.00),
     (3, 3, 15.000, 12000.00),
     (3, 5, 8.000, 18000.00);