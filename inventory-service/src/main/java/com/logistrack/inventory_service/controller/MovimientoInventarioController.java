package com.logistrack.inventory_service.controller;

import com.logistrack.inventory_service.dto.MovimientoRequestDTO;
import com.logistrack.inventory_service.dto.MovimientoResponseDTO;
import com.logistrack.inventory_service.service.MovimientoInventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoService.obtenerTodos());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<MovimientoResponseDTO>> obtenerPorProducto (@PathVariable Long productoId) {
        return ResponseEntity.ok(movimientoService.obtenerPorProducto(productoId));
    }

    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> registrar (@Valid @RequestBody MovimientoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.registrar(dto));
    }


}
