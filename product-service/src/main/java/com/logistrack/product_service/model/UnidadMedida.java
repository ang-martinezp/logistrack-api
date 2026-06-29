package com.logistrack.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidades_medida")


public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "La abreviatura no puede estar vacia")
    @Column(nullable = false, unique = true, length = 10)
    private String abreviatura;
}
