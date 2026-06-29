package com.logistrack.inventory_service.service;

import com.logistrack.inventory_service.model.AlertaStock;
import com.logistrack.inventory_service.repository.AlertaStockRepository;
import com.logistrack.inventory_service.repository.StockRepository;
import com.logistrack.inventory_service.model.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlertaStockService {

    private final AlertaStockRepository alertaRepository;
    private final StockRepository stockRepository;

    public List<AlertaStock> obtenerTodas(){
        log.info("Obteniendo todas las alertas");
        return alertaRepository.findAll();
    }

    public List<AlertaStock> obtenerActivas(){
        log.info("Obteniendo alertas activas");
        return alertaRepository.findByEstado(AlertaStock.EstadoAlerta.ACTIVA);
    }

    public void verificarYGenerarAlertas(){
        log.info("Verificando stock minimo para generar alertas");
        List<Stock> stocks = stockRepository.findAll();
        for (Stock stock : stocks){
            if (stock.getCantidadActual().compareTo(stock.getCantidadMinima())< 0) {
                AlertaStock alerta = new AlertaStock();
                alerta.setMensaje("Stock bajo para producto id: " + stock.getProductoId()
                        + ". Actual: " + stock.getCantidadActual()
                        + ". Minimo: " + stock.getCantidadMinima());
                alerta.setEstado(AlertaStock.EstadoAlerta.ACTIVA);
                alerta.setFechaCreacion(LocalDateTime.now());
                alertaRepository.save(alerta);
                log.warn("Alerta generada para producto id: {}", stock.getProductoId());
            }
        }
    }

    public void resolverAlerta(Long id) {
        log.info("Resolviendo alerta id: {}", id);
        AlertaStock alerta = alertaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Alerta no encontrada con id: " + id));
        alerta.setEstado(AlertaStock.EstadoAlerta.RESUELTA);
        alerta.setFechaResolucion(LocalDateTime.now());
        alertaRepository.save(alerta);
    }
}
