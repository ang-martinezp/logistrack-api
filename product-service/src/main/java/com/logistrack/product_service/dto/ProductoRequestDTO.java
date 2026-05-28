package com.logistrack.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoRequestDTO {

    @NotBlank(message = "El SKU no puede estar vacio")
    private String sku;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "La categoria es obligatoria")
    private Long categoriaId;

    @NotNull(message = "La unidad de medida es obligatoria")
    private Long unidadMedidaId;

    private BigDecimal pesoKg;

}
