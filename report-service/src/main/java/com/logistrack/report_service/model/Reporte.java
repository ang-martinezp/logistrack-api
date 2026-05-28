package com.logistrack.report_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del reporte no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El tipo de reporte no puede estar vacio")
    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    @NotBlank(message = "El contenido del reporte no puede estar vacio")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

}
