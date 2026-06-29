package com.logistrack.inventory_service.controller;

import com.logistrack.inventory_service.dto.StockRequestDTO;
import com.logistrack.inventory_service.dto.StockResponseDTO;
import com.logistrack.inventory_service.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> listarTodos(){
        return ResponseEntity.ok(stockService.obtenerTodos());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<StockResponseDTO> obtenerPorProducto(@PathVariable Long productoId){
        return ResponseEntity.ok(stockService.obtenerPorProductoId(productoId));
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> crear(@Valid @RequestBody StockRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> actualizar(@PathVariable Long id,
                                                       @Valid @RequestBody StockRequestDTO dto) {
        return ResponseEntity.ok(stockService.actualizar(id, dto));
    }
}
