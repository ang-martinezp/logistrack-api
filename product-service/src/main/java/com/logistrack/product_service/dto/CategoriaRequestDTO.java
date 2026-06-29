package com.logistrack.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    private String descripcion;

}
