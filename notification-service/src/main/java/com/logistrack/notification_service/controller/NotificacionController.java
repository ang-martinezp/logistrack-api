package com.logistrack.notification_service.controller;

import com.logistrack.notification_service.dto.NotificacionRequestDTO;
import com.logistrack.notification_service.dto.NotificacionResponseDTO;
import com.logistrack.notification_service.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(notificacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.obtenerPorId(id));
    }

    @GetMapping("/destinatario/{destinatario}")
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerPorDestinatario(@PathVariable String destinatario) {
        return ResponseEntity.ok(notificacionService.obtenerPorDestinatario(destinatario));
    }

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.crear(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<NotificacionResponseDTO> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(notificacionService.actualizarEstado(id, estado));
    }

}
