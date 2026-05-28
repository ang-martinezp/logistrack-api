package com.logistrack.notification_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacio")
    @Column(nullable = false, length = 500)
    private String mensaje;

    @NotBlank(message = "El destinatario no puede estar vacio")
    @Column(nullable = false, length = 100)
    private String destinatario;

    @NotBlank(message = "El estado no puede estar vacio")
    @Column(nullable = false, length = 50)
    private String estado;

}
