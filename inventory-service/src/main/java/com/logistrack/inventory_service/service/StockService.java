package com.logistrack.inventory_service.service;

import com.logistrack.inventory_service.client.ProductoFeignClient;
import com.logistrack.inventory_service.dto.ProductoDTO;
import com.logistrack.inventory_service.dto.StockRequestDTO;
import com.logistrack.inventory_service.dto.StockResponseDTO;
import com.logistrack.inventory_service.model.Stock;
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
public class StockService {

    private final StockRepository stockRepository;
    private final ProductoFeignClient productoFeignClient;

    private StockResponseDTO mapToDTO(Stock stock) {
        ProductoDTO producto = null;
        try {
            producto = productoFeignClient.obtenerProductoPorId(stock.getProductoId());
        } catch (Exception e) {
            log.warn("No se pudo obtener producto id: {}", stock.getProductoId());
        }
        return new StockResponseDTO(
                stock.getId(),
                producto,
                stock.getCantidadActual(),
                stock.getCantidadMinima(),
                stock.getUltimaActualizacion()
        );
    }

    public List<StockResponseDTO> obtenerTodos() {
        log.info("Obteniendo todo el stock");
        return stockRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public StockResponseDTO obtenerPorProductoId(Long productoId) {
        log.info("Buscando stock para producto id: {}", productoId);
        Stock stock = stockRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado para producto id: " + productoId));
        return mapToDTO(stock);
    }

    public StockResponseDTO crear(StockRequestDTO dto) {
        log.info("Creando stock para producto id: {}", dto.getProductoId());
        if (stockRepository.findByProductoId(dto.getProductoId()).isPresent()) {
            throw new RuntimeException("Ya existe stock para el producto id: " + dto.getProductoId());
        }
        Stock stock = new Stock();
        stock.setProductoId(dto.getProductoId());
        stock.setCantidadMinima(dto.getCantidadMinima());
        stock.setUltimaActualizacion(LocalDateTime.now());
        return mapToDTO(stockRepository.save(stock));
    }

    public StockResponseDTO actualizar(Long id, StockRequestDTO dto) {
        log.info("Actualizando stock id: {}", id);
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado con id: " + id));
        stock.setCantidadMinima(dto.getCantidadMinima());
        stock.setUltimaActualizacion(LocalDateTime.now());
        return mapToDTO(stockRepository.save(stock));
    }
}