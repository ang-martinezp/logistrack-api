package com.logistrack.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El email debe tener formato válido (hola@hola.com)")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotNull(message = "El rol es obligatorio (ADMIN, BODEGUERO, SUPERVISOR, TRANSPORTISTA)")
    private String rol;


}
