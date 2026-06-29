package com.logistrack.dispatch_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespachoRequestDTO {

    @NotNull(message = "El ID del pedido es obligatorio")
    private Long pedidoId;

    @NotBlank(message = "La dirección de destino es obligatoria")
    private String direccionDestino;

    @NotBlank(message = "El transportista es obligatorio")
    private String transportista;

}
