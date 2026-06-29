package com.logistrack.receiving_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"recepcionId", "pedido", "proveedor", "estado", "fechaRecepcion", "observaciones", "detalles"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionResponseDTO {

    private Long recepcionId;
    private PedidoDTO pedido;
    private ProveedorDTO proveedor;
    private String estado;
    private LocalDateTime fechaRecepcion;
    private String observaciones;
    private List<DetalleRecepcionResponseDTO> detalles;
}