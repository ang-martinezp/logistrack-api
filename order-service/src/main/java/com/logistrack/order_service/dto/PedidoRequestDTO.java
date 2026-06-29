package com.logistrack.order_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    @NotNull(message = "El proveedor es obligatorio")
    private Long proveedorId;
    private LocalDateTime fechaEntregaEsperada;
    private String observaciones;


}
