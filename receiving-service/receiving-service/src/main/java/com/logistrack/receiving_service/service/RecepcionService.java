package com.logistrack.receiving_service.service;

import com.logistrack.receiving_service.dto.*;
import com.logistrack.receiving_service.entity.Recepcion;
import com.logistrack.receiving_service.entity.DetalleRecepcion;
import com.logistrack.receiving_service.repository.RecepcionRepository;
import com.logistrack.receiving_service.repository.DetalleRecepcionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecepcionService {

    private final RecepcionRepository recepcionRepository;
    private final DetalleRecepcionRepository detalleRecepcionRepository;

    public List<RecepcionResponseDTO> listarTodas() {
        log.info("Listando todas las recepciones");
        return recepcionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RecepcionResponseDTO obtenerPorId(Long id) {
        log.info("Buscando recepcion con id: {}", id);
        Recepcion recepcion = recepcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepcion no encontrada con id: " + id));
        return mapToDTO(recepcion);
    }

    public List<RecepcionResponseDTO> obtenerPorEstado(Recepcion.EstadoRecepcion estado) {
        log.info("Buscando recepciones con estado: {}", estado);
        return recepcionRepository.findByEstado(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<RecepcionResponseDTO> obtenerPorProveedor(Long proveedorId) {
        log.info("Buscando recepciones del proveedor: {}", proveedorId);
        return recepcionRepository.findByProveedorId(proveedorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RecepcionResponseDTO crear(RecepcionRequestDTO dto) {
        log.info("Creando recepcion para pedido: {}", dto.getPedidoId());
        Recepcion recepcion = new Recepcion();
        recepcion.setPedidoId(dto.getPedidoId());
        recepcion.setProveedorId(dto.getProveedorId());
        recepcion.setObservaciones(dto.getObservaciones());
        recepcion.setEstado(Recepcion.EstadoRecepcion.PENDIENTE);
        recepcion.setFechaRecepcion(LocalDateTime.now());
        return mapToDTO(recepcionRepository.save(recepcion));
    }

    public RecepcionResponseDTO actualizarEstado(Long id, Recepcion.EstadoRecepcion nuevoEstado) {
        log.info("Actualizando estado de recepcion {} a {}", id, nuevoEstado);
        Recepcion recepcion = recepcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepcion no encontrada con id: " + id));
        recepcion.setEstado(nuevoEstado);
        return mapToDTO(recepcionRepository.save(recepcion));
    }

    public void eliminar(Long id) {
        log.info("Eliminando recepcion con id: {}", id);
        recepcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepcion no encontrada con id: " + id));
        recepcionRepository.deleteById(id);
    }

    private RecepcionResponseDTO mapToDTO(Recepcion recepcion) {
        List<DetalleRecepcionResponseDTO> detalles = recepcion.getDetalles() == null ? List.of() :
                recepcion.getDetalles()
                        .stream()
                        .map(d -> new DetalleRecepcionResponseDTO(
                                d.getId(),
                                d.getProductoId(),
                                d.getCantidadEsperada(),
                                d.getCantidadRecibida()))
                        .collect(Collectors.toList());

        return new RecepcionResponseDTO(
                recepcion.getId(),
                recepcion.getPedidoId(),
                recepcion.getProveedorId(),
                recepcion.getEstado().name(),
                recepcion.getFechaRecepcion(),
                recepcion.getObservaciones(),
                detalles
        );
    }
}