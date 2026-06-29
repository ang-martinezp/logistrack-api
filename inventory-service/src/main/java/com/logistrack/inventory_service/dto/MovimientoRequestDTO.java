package com.logistrack.inventory_service.dto;

import com.logistrack.inventory_service.model.MovimientoInventario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequestDTO {

    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private MovimientoInventario.TipoMovimiento tipo;

    @NotNull(message = "La cantidad es obligatoria")
    private BigDecimal cantidad;
    private String motivo;
}
