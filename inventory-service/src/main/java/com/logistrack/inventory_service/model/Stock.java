package com.logistrack.inventory_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El producto es obligatorio")
    @Column(name= "producto_id", nullable = false, unique = true)
    private Long productoId;

    @Column(name= "cantidad_actual", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidadActual = BigDecimal.ZERO;

    @Column(name = "cantidad_minima", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidadMinima = BigDecimal.ZERO;

    @Column (name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

}
