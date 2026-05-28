package com.logistrack.order_service.service;

import com.logistrack.order_service.dto.DetallePedidoRequestDTO;
import com.logistrack.order_service.dto.DetallePedidoResponseDTO;
import com.logistrack.order_service.dto.PedidoRequestDTO;
import com.logistrack.order_service.dto.PedidoResponseDTO;
import com.logistrack.order_service.model.DetallePedido;
import com.logistrack.order_service.model.Pedido;
import com.logistrack.order_service.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private DetallePedidoResponseDTO mapDEtalleToDTO(DetallePedido detalle){
        return new DetallePedidoResponseDTO(
                detalle.getId(),
                detalle.getProductoId(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario()
        );
    }

    private PedidoResponseDTO mapToDTO(Pedido pedido) {
        List<DetallePedidoResponseDTO> detalles = pedido.getDetalles() == null ? List.of() :
                pedido.getDetalles().stream()
                .map(this::mapDEtalleToDTO)
                .collect(Collectors.toList());
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getProveedorId(),
                pedido.getEstado(),
                pedido.getFechaPedido(),
                pedido.getFechaEntregaEsperada(),
                pedido.getObservaciones(),
                detalles
        );
    }

    public List<PedidoResponseDTO> obtenerTodos(){
        log.info("Obteniendo todos los pedidos");
        return pedidoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO obtenerPorId(Long id){
        log.info("Buscando pedido id: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pedido no encontrado con id: " + id));
        return mapToDTO(pedido);
    }

    public List<PedidoResponseDTO> obtenerPorEstado(Pedido.EstadoPedido estado){
        log.info("Obteniendo pedidos con estado: {}", estado);
        return pedidoRepository.findByEstado(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO crear(PedidoRequestDTO dto, List<DetallePedidoRequestDTO> detallesDTO) {
        log.info("Creando pedido para proveedor id: {}", dto.getProveedorId());
        Pedido pedido = new Pedido();
        pedido.setProveedorId(dto.getProveedorId());
        pedido.setEstado(Pedido.EstadoPedido.PENDIENTE);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setFechaEntregaEsperada(dto.getFechaEntregaEsperada());
        pedido.setObservaciones(dto.getObservaciones());

        List<DetallePedido> detalles = detallesDTO.stream().map(d -> {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProductoId(d.getProductoId());
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());
            return detalle;
        }).collect(Collectors.toList());

        pedido.setDetalles(detalles);
        return mapToDTO(pedidoRepository.save(pedido));
    }

    public PedidoResponseDTO actualizarEstado(Long id, Pedido.EstadoPedido nuevoEstado){
        log.info("Actualizando estado del pedido id: {} a {}", id, nuevoEstado );
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        pedido.setEstado(nuevoEstado);
        return mapToDTO(pedidoRepository.save(pedido));
    }

    public void cancelar(Long id){
        log.info("Cancelando pedido id: {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pedido no encontrado con id: "+ id));
        if (pedido.getEstado() == Pedido.EstadoPedido.ENTREGADO){
            throw new RuntimeException("No se puede cancelar un pedido ya entregado");
        }
        pedido.setEstado(Pedido.EstadoPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }

}
