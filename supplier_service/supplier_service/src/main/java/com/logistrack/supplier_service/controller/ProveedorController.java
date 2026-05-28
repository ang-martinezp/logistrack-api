package com.logistrack.supplier_service.controller;

import com.logistrack.supplier_service.dto.ProveedorRequestDTO;
import com.logistrack.supplier_service.dto.ProveedorResponseDTO;
import com.logistrack.supplier_service.model.Proveedor;
import com.logistrack.supplier_service.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> listarTodos(){
        return ResponseEntity.ok(proveedorService.listarTodos());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ProveedorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProveedorResponseDTO>> buscarPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(proveedorService.buscarPorNombre(nombre));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProveedorResponseDTO>> obtenerPorEstado (@PathVariable Proveedor.EstadoProveedor estado) {
        return ResponseEntity.ok(proveedorService.obtenerPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crear (
            @Valid @RequestBody ProveedorRequestDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizar(@PathVariable Long id,
                                                           @Valid @RequestBody ProveedorRequestDTO dto){
        return ResponseEntity.ok(proveedorService.actualizar(id, dto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ProveedorResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam Proveedor.EstadoProveedor nuevoEstado) {
        return ResponseEntity.ok(proveedorService.cambiarEstado(id, nuevoEstado));
    }

}
