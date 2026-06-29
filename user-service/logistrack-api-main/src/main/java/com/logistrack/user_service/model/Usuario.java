package com.logistrack.user_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String username;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    public enum RolUsuario {
        ADMIN, BODEGUERO, SUPERVISOR, TRANSPORTISTA
    }

    public enum EstadoUsuario {
        ACTIVO, INACTIVO, BLOQUEADO
    }
}

