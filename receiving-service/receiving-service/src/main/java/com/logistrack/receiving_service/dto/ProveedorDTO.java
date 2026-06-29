package com.logistrack.receiving_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {
    private Long proveedorId;
    private String nombre;
    private String email;
    private String telefono;
    private String estado;
}