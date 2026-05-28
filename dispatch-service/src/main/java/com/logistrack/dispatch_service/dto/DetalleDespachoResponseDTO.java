package com.logistrack.dispatch_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDespachoResponseDTO {

    @JsonProperty("detalleId")
    private Long detalleId;
    private Long productoId;
    private BigDecimal cantidad;

}
