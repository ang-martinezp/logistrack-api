INSERT INTO categorias (nombre, descripcion, activo) VALUES
 ('Electronica', 'Equipos y componentes electronicos', TRUE),
 ('Herramientas', 'Herramientas y manuales electronicos', TRUE),
 ('Consumibles', 'Materiales de uso frecuente', TRUE),
 ('Repuestos', 'Piezas de repuesto y mantenimiento', TRUE);

INSERT INTO unidades_medida (nombre, abreviatura) VALUES
 ('Unidad', 'UN'),
 ('Kilogramo', 'KG'),
 ('Litro', 'LT'),
 ('Metro', 'MT'),
 ('Caja', 'CJ');

INSERT INTO productos (sku, nombre, descripcion, categoria_id, unidad_medida_id, peso_kg, activo) VALUES
 ('SKU-001', 'Laptop DELL Latitude', 'Laptop Ofice 14 pulgadas', 1,1,1.800,TRUE),
 ('SKU-002', 'Teclado Kumara USB', 'Teclado Mecanico USB', 1,1,0.500,TRUE ),
 ('SKU-003', 'Pasta Termica Artic MX4', 'Pasta termica para repuesto', 3,1,0.30,   TRUE),
 ('SKU-004', 'Papel A4 resma', 'Resma 500 hojas 75g', 3,5,2.300, TRUE),
 ('SKU-005', 'Liquido refrigerante Gamer', 'Liquido desmineralizado para refrigeraciones liquidas', 4, 3, 0.500, TRUE );