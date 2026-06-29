package com.logistrack.report_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRequestDTO {

    @NotBlank(message = "El nombre del reporte no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El tipo de reporte no puede estar vacio")
    private String tipo;

    @NotBlank(message = "El contenido del reporte no puede estar vacio")
    private String contenido;

}
