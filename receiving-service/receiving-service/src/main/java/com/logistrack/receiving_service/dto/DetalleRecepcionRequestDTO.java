package com.logistrack.receiving_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRecepcionRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad esperada es obligatoria")
    private BigDecimal cantidadEsperada;

    @NotNull(message = "La cantidad recibida es obligatoria")
    private BigDecimal cantidadRecibida;


}
