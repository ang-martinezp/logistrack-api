package com.logistrack.supplier_service.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@JsonPropertyOrder({"proveedorId", "nombre", "rut", "email", "telefono", "direccion", "contacto", "estado"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponseDTO {

    private Long proveedorId;
    private String nombre;
    private String rut;
    private String email;
    private String telefono;
    private String direccion;
    private String contacto;
    private String estado;
}
