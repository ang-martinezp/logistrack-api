package com.logistrack.dispatch_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.swing.table.DefaultTableCellRenderer;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "despachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    @Enumerated(EnumType.STRING)
    private EstadoDespacho estado;
    private LocalDateTime fechaDespacho;
    private LocalDateTime fechaEntrega;
    private String direccionDestino;
    private String transportista;

    @OneToMany(mappedBy = "despacho", cascade = CascadeType.ALL)
    private List<DetalleDespacho> detalles;

    public enum EstadoDespacho {
        PREPARANDO, EN_TRANSITO, ENTREGADO, CANCELADO
    }
}
