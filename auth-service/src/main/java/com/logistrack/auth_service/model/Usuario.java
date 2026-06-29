package com.logistrack.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Column(nullable = false, length = 255)
    private String password;

    @NotBlank(message = "El correo no puede estar vacio")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "El rol no puede estar vacio")
    @Column(nullable = false, length = 50)
    private String rol;

    @Column(nullable = false)
    private Boolean activo = true;

}
