package com.logistrack.receiving_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@JsonPropertyOrder({"detalleRecepcionId", "productoId", "cantidadEsperada", "cantidadRecibida"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRecepcionResponseDTO {

    private Long detalleRecepcionId;
    private Long productoId;
    private BigDecimal cantidadEsperada;
    private BigDecimal cantidadRecibida;

}
