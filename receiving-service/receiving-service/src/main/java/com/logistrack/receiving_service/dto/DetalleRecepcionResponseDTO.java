package com.logistrack.receiving_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@JsonPropertyOrder({"detalleRecepcionId", "producto", "cantidadEsperada", "cantidadRecibida"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRecepcionResponseDTO {

    private Long detalleRecepcionId;
    private ProductoDTO producto;
    private BigDecimal cantidadEsperada;
    private BigDecimal cantidadRecibida;
}