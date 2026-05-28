package com.logistrack.product_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaResponseDTO {
    private long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
