package com.logistrack.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({"authId", "username", "email", "rol", "activo"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    @JsonProperty("authId")
    private Long authId;
    private String username;
    private String email;
    private String rol;
    private Boolean activo;

}
