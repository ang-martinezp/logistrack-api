package com.logistrack.dispatch_service.controller;

import com.logistrack.dispatch_service.dto.DespachoRequestDTO;
import com.logistrack.dispatch_service.dto.DespachoResponseDTO;
import com.logistrack.dispatch_service.entity.Despacho;
import com.logistrack.dispatch_service.service.DespachoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/despachos")
@RequiredArgsConstructor
public class DespachoController {

    private final DespachoService despachoService;

    @GetMapping
    public ResponseEntity<List<DespachoResponseDTO>> listarTodos(){
        return ResponseEntity.ok(despachoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespachoResponseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(despachoService.obtenerPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DespachoResponseDTO>> obtenerPorEstado(
            @PathVariable Despacho.EstadoDespacho estado){
        return ResponseEntity.ok(despachoService.obtenerPorEstado(estado));
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DespachoResponseDTO>> obtenerPorPedido(
            @PathVariable Long pedidoId){
        return ResponseEntity.ok(despachoService.obtenerPorPedido(pedidoId));
    }

    @PostMapping
    public ResponseEntity<DespachoResponseDTO> crear(
            @Valid @RequestBody DespachoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(despachoService.crear(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<DespachoResponseDTO> actualizarEstado (
            @PathVariable Long id,
            @RequestParam Despacho.EstadoDespacho nuevoEstado){
        return ResponseEntity.ok(despachoService.actualizarEstado(id, nuevoEstado));
    }
}
