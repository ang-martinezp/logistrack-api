package com.logistrack.user_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@JsonPropertyOrder({"usuarioId", "nombre", "apellido", "email", "username", "rol", "estado"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long usuarioId;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String rol;
    private String estado;

}
