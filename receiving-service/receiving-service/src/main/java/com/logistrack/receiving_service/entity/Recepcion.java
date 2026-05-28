package com.logistrack.receiving_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recepciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recepcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    private Long proveedorId;

    @Enumerated(EnumType.STRING)
    private EstadoRecepcion estado;

    private LocalDateTime fechaRecepcion;
    private String observaciones;

    @OneToMany(mappedBy = "recepcion", cascade = CascadeType.ALL)
    private List<DetalleRecepcion> detalles;

    public enum EstadoRecepcion {
        PENDIENTE, PARCIAL, COMPLETA, RECHAZADA
    }

}
