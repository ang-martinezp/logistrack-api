package com.logistrack.receiving_service.controller;

import com.logistrack.receiving_service.dto.RecepcionRequestDTO;
import com.logistrack.receiving_service.dto.RecepcionResponseDTO;
import com.logistrack.receiving_service.entity.Recepcion;
import com.logistrack.receiving_service.service.RecepcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recepciones")
@RequiredArgsConstructor
public class RecepcionController {

    private final RecepcionService recepcionService;

    @GetMapping
    public ResponseEntity<List<RecepcionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(recepcionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recepcionService.obtenerPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<RecepcionResponseDTO>> obtenerPorEstado(
            @PathVariable Recepcion.EstadoRecepcion estado) {
        return ResponseEntity.ok(recepcionService.obtenerPorEstado(estado));
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<RecepcionResponseDTO>> obtenerPorProveedor(
            @PathVariable Long proveedorId) {
        return ResponseEntity.ok(recepcionService.obtenerPorProveedor(proveedorId));
    }

    @PostMapping
    public ResponseEntity<RecepcionResponseDTO> crear(
            @Valid @RequestBody RecepcionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recepcionService.crear(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<RecepcionResponseDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Recepcion.EstadoRecepcion nuevoEstado) {
        return ResponseEntity.ok(recepcionService.actualizarEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recepcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}