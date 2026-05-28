package com.logistrack.dispatch_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_despacho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDespacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Long ProductoId;
    private BigDecimal cantidad;

    @ManyToOne
    @JoinColumn(name = "despacho_id")
    private Despacho despacho;
}
