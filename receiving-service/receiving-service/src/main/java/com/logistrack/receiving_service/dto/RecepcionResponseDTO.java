package com.logistrack.receiving_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.logistrack.receiving_service.entity.DetalleRecepcion;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"recepcionId", "pedidoId", "proveedorId", "estado", "fechaRecepcion", "observaciones", "detalles"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionResponseDTO {

    private Long recepcionId;
    private Long pedidoId;
    private Long proveedorId;
    private String estado;
    private LocalDateTime fechaRecepcion;
    private String observaciones;
    private List<DetalleRecepcionResponseDTO> detalles;

}
