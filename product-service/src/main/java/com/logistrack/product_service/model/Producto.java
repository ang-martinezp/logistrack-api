package com.logistrack.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = " El SKU no puede estar vacio")
    @Column(nullable = false,unique = true, length = 50)
    private String sku;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "La categorìa es obligatoria")
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull(message = "La unidad de medida es obligatoria")
    @ManyToOne
    @JoinColumn(name ="unidad_medida_id")
    private UnidadMedida unidadMedida;

    @Column(name = "peso_kg", precision = 8, scale = 3)
    private BigDecimal pesoKg;

    @Column(nullable = false)
    private Boolean activo = true;

}


