package com.logistrack.order_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;
    @NotNull(message = "La cantidad es obligatoria")
    private BigDecimal cantidad;
    @NotNull(message = "El precio unitario es obligatorio")
    private BigDecimal precioUnitario;
}
