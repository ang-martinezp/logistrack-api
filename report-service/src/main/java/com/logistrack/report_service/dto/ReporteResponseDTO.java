package com.logistrack.report_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import java.time.LocalDateTime;

@JsonPropertyOrder({"reportId", "nombre", "tipo", "fechaGeneracion", "contenido"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDTO {

    @JsonProperty("reportId")
    private Long reportId;
    private String nombre;
    private String tipo;
    private LocalDateTime fechaGeneracion;
    private String contenido;

}
