package com.logistrack.supplier_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rut;
    private String email;
    private String telefono;
    private String direccion;
    private String contacto;

    @Enumerated(EnumType.STRING)
    private EstadoProveedor estado;

    public enum EstadoProveedor {
        ACTIVO, INACTIVO, BLOQUEADO
    }
}
