package com.logistrack.order_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String sku;
    private String nombre;
    private String categoriaNombre;
    private String unidadMedidaNombre;
}