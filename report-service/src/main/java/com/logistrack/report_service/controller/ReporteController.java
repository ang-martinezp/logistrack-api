package com.logistrack.report_service.controller;

import com.logistrack.report_service.dto.ReporteRequestDTO;
import com.logistrack.report_service.dto.ReporteResponseDTO;
import com.logistrack.report_service.service.ReporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(reporteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(reporteService.obtenerPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<ReporteResponseDTO> crear(@Valid @RequestBody ReporteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.crear(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ReporteRequestDTO dto) {
        return ResponseEntity.ok(reporteService.actualizar(id, dto));
    }

}
