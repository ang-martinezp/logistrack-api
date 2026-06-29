package com.logistrack.inventory_service.dto;

import com.logistrack.inventory_service.model.MovimientoInventario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoResponseDTO {

    private Long id;
    private ProductoDTO producto;
    private MovimientoInventario.TipoMovimiento tipo;
    private BigDecimal cantidad;
    private String motivo;
    private LocalDateTime fechaMovimiento;
}