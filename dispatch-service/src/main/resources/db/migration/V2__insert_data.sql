INSERT INTO despachos (pedido_id, estado, fecha_despacho, fecha_entrega, direccion_destino, transportista) VALUES
   (1, 'EN_TRANSITO', NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), 'Av. Principal 123, Ciudad', 'TransExpress'),
   (2, 'PREPARANDO', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 'Calle Secundaria 456, Ciudad', 'LogiRapid'),
   (3, 'ENTREGADO', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_SUB(NOW(), INTERVAL 1 DAY), 'Zona Industrial 789, Ciudad', 'TransExpress');

INSERT INTO detalles_despacho (despacho_id, producto_id, cantidad) VALUES
    (1, 1, 5.000),
    (1, 2, 10.000),
    (2, 3, 3.000),
    (3, 1, 2.000);