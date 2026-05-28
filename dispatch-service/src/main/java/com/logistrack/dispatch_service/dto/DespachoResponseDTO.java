package com.logistrack.dispatch_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.logistrack.dispatch_service.entity.DetalleDespacho;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"despachoId", "pedidoId", "estado", "fechaDespacho", "fechaEntrega", "dreccionDestino", "transportista", "detalles"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DespachoResponseDTO {

    @JsonProperty("despachoId")
    private Long despachoId;
    private Long pedidoId;
    private String estado;
    private LocalDateTime fechaDespacho;
    private LocalDateTime fechaEntrega;
    private String direccionDestino;
    private String transportista;
    private List<DetalleDespachoResponseDTO> detalles;

}
