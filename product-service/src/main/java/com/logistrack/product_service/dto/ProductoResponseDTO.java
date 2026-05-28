package com.logistrack.product_service.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoResponseDTO {

    private Long id;
    private String sku;
    private String nombre;
    private String descripcion;
    private String categoriaNombre;
    private String unidadMedidaNombre;
    private BigDecimal pesoKg;
    private Boolean activo;

}
