package com.logistrack.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoResponseDTO {

    private Long id;
    private Long productoId;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
}
