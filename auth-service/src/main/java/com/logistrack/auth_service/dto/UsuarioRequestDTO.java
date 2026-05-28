package com.logistrack.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

    @NotBlank(message = "El correo no puede estar vacio")
    private String email;

    @NotBlank(message = "El rol no puede estar vacio")
    private String rol;

}
