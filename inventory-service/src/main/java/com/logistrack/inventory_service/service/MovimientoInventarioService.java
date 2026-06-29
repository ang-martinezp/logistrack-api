package com.logistrack.inventory_service.service;

import com.logistrack.inventory_service.client.ProductoFeignClient;
import com.logistrack.inventory_service.dto.MovimientoRequestDTO;
import com.logistrack.inventory_service.dto.MovimientoResponseDTO;
import com.logistrack.inventory_service.dto.ProductoDTO;
import com.logistrack.inventory_service.model.MovimientoInventario;
import com.logistrack.inventory_service.model.Stock;
import com.logistrack.inventory_service.repository.MovimientoInventarioRepository;
import com.logistrack.inventory_service.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final StockRepository stockRepository;
    private final ProductoFeignClient productoFeignClient;

    private MovimientoResponseDTO mapToDTO(MovimientoInventario m) {
        ProductoDTO producto = null;
        try {
            producto = productoFeignClient.obtenerProductoPorId(m.getProductoId());
        } catch (Exception e) {
            log.warn("No se pudo obtener producto id: {}", m.getProductoId());
        }
        return new MovimientoResponseDTO(
                m.getId(),
                producto,
                m.getTipo(),
                m.getCantidad(),
                m.getMotivo(),
                m.getFechaMovimiento()
        );
    }

    public List<MovimientoResponseDTO> obtenerTodos() {
        log.info("Obteniendo todos los movimientos");
        return movimientoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<MovimientoResponseDTO> obtenerPorProducto(Long productoId) {
        log.info("Obteniendo movimientos para producto id: {}", productoId);
        return movimientoRepository.findByProductoId(productoId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public MovimientoResponseDTO registrar(MovimientoRequestDTO dto) {
        log.info("Registrando movimiento tipo: {} para producto id: {}", dto.getTipo(), dto.getProductoId());
        Stock stock = stockRepository.findByProductoId(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Stock no encontrado para producto id: " + dto.getProductoId()));

        if (dto.getTipo() == MovimientoInventario.TipoMovimiento.ENTRADA) {
            stock.setCantidadActual(stock.getCantidadActual().add(dto.getCantidad()));
        } else {
            if (stock.getCantidadActual().compareTo(dto.getCantidad()) < 0) {
                throw new RuntimeException("Stock insuficiente. Disponible: " + stock.getCantidadActual());
            }
            stock.setCantidadActual(stock.getCantidadActual().subtract(dto.getCantidad()));
        }
        stock.setUltimaActualizacion(LocalDateTime.now());
        stockRepository.save(stock);

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProductoId(dto.getProductoId());
        movimiento.setTipo(dto.getTipo());
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setMotivo(dto.getMotivo());
        movimiento.setFechaMovimiento(LocalDateTime.now());

        return mapToDTO(movimientoRepository.save(movimiento));
    }
}