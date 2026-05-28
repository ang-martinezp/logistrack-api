package com.logistrack.inventory_service.controller;

import com.logistrack.inventory_service.model.AlertaStock;
import com.logistrack.inventory_service.service.AlertaStockService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
public class AlertaStockController {

    private final AlertaStockService alertaService;

    @GetMapping
    public ResponseEntity<List<AlertaStock>> listarTodas(){
        return ResponseEntity.ok(alertaService.obtenerTodas());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<AlertaStock>> listarActivas(){
        return ResponseEntity.ok(alertaService.obtenerActivas());
    }

    @PostMapping("/verificar")
    public ResponseEntity<Void> verificar(){
        alertaService.verificarYGenerarAlertas();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<Void> resolver(@PathVariable Long id){
        alertaService.resolverAlerta(id);
        return ResponseEntity.ok().build();
    }
}
