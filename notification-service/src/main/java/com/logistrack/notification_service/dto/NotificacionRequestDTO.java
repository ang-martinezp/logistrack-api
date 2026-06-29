package com.logistrack.notification_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequestDTO {

    @NotBlank(message = "El título no puede estar vacio")
    private String titulo;

    @NotBlank(message = "El mensaje no puede estar vacio")
    private String mensaje;

    @NotBlank(message = "El destinatario no puede estar vacio")
    private String destinatario;

    @NotBlank(message = "El estado no puede estar vacio")
    private String estado;

}
