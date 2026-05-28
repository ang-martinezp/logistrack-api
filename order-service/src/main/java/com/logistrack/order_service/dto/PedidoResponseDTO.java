package com.logistrack.order_service.dto;

import com.logistrack.order_service.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private Long proveedorId;
    private Pedido.EstadoPedido estado;
    private LocalDateTime fechaPedido;
    private LocalDateTime fechaEntregaEsperada;
    private String observaciones;
    private List<DetallePedidoResponseDTO> detalles;
}
