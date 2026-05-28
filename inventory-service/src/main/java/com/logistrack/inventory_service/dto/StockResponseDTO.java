package com.logistrack.inventory_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {

    private Long id;
    private Long productoId;
    private BigDecimal cantidadActual;
    private BigDecimal cantidadMinima;
    private LocalDateTime ultimaActualizacion;

}
