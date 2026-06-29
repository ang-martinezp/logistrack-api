package com.logistrack.notification_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({"notificationId", "titulo", "mensaje", "destinatario", "estado"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionResponseDTO {

    @JsonProperty("notificationId")
    private Long notificationId;
    private String titulo;
    private String mensaje;
    private String destinatario;
    private String estado;

}
