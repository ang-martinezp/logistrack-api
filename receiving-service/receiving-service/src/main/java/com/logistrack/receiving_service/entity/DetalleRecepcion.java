package com.logistrack.receiving_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_recepcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRecepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;
    private BigDecimal cantidadEsperada;
    private BigDecimal cantidadRecibida;

    @ManyToOne
    @JoinColumn(name = "recepcion_id")
    private Recepcion recepcion;

}
