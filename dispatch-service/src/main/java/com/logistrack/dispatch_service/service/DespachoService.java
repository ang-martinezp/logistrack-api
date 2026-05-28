package com.logistrack.dispatch_service.service;

import com.logistrack.dispatch_service.dto.*;
import com.logistrack.dispatch_service.entity.Despacho;
import com.logistrack.dispatch_service.repository.DespachoRepository;
import com.logistrack.dispatch_service.repository.DetalleDespachoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DespachoService {

    private final DespachoRepository despachoRepository;
    private final DetalleDespachoRepository detalleDespachoRepository;

    public List<DespachoResponseDTO> listarTodos(){
        log.info("Listando todos los despachos");
        return despachoRepository.findAll()
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    public DespachoResponseDTO obtenerPorId(Long id){
        log.info("Buscando despacho con id: {}", id);
        Despacho despacho = despachoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Despacho no encontrado con id: " +  id));
        return mapToDTO(despacho);
    }

    public List<DespachoResponseDTO> obtenerPorEstado(Despacho.EstadoDespacho estado) {
        log.info("Buscando despachos con estado: {}", estado);
        return despachoRepository.findByEstado(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<DespachoResponseDTO> obtenerPorPedido(Long pedidoId){
        log.info("Buscando despacho para el pedido ID: {}", pedidoId);
        return despachoRepository.findByPedidoId(pedidoId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DespachoResponseDTO crear(DespachoRequestDTO dto){
        log.info("Creando despacho para pedido: {}", dto.getPedidoId());
        Despacho despacho = new Despacho();
        despacho.setPedidoId(dto.getPedidoId());
        despacho.setDireccionDestino(dto.getDireccionDestino());
        despacho.setTransportista(dto.getTransportista());
        despacho.setEstado(Despacho.EstadoDespacho.PREPARANDO);
        despacho.setFechaDespacho(LocalDateTime.now());
        return mapToDTO(despachoRepository.save(despacho));
    }

    public DespachoResponseDTO actualizarEstado(Long id, Despacho.EstadoDespacho nuevoEstado) {
        log.info("Actualizando estado del despacho {} a {}", id, nuevoEstado);
        Despacho despacho = despachoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado con id: " + id));
        despacho.setEstado(nuevoEstado);
        if (nuevoEstado == Despacho.EstadoDespacho.ENTREGADO) {
            despacho.setFechaEntrega(LocalDateTime.now());
        }
        return mapToDTO(despachoRepository.save(despacho));
    }

    private DespachoResponseDTO mapToDTO(Despacho despacho) {
        List<DetalleDespachoResponseDTO> detalles = despacho.getDetalles() == null ? List.of() :
                despacho.getDetalles()
                        .stream()
                        .map(d -> new DetalleDespachoResponseDTO(d.getId(), d.getProductoId(), d.getCantidad()))
                        .collect(Collectors.toList());
        return new DespachoResponseDTO(
                despacho.getId(),
                despacho.getPedidoId(),
                despacho.getEstado().name(),
                despacho.getFechaDespacho(),
                despacho.getFechaEntrega(),
                despacho.getDireccionDestino(),
                despacho.getTransportista(),
                detalles
        );
    }
}
