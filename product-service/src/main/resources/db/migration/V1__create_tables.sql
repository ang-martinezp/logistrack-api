CREATE TABLE categorias (
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE,
    descripcion TEXT,
    activo BOOLEAN NOT NULL  DEFAULT TRUE
);

CREATE TABLE unidades_medida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    abreviatura VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE productos (
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    categoria_id BIGINT,
    unidad_medida_id BIGINT,
    peso_kg DECIMAL(8,3),
    activo TINYINT(1) NOT NULL DEFAULT 1,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (unidad_medida_id)  REFERENCES unidades_medida(id)
);

