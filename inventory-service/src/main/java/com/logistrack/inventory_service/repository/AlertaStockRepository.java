package com.logistrack.inventory_service.repository;

import com.logistrack.inventory_service.model.AlertaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertaStockRepository extends JpaRepository<AlertaStock, Long>{
    List<AlertaStock> findByProductoId(Long productoId);
    List<AlertaStock> findByEstado(AlertaStock.EstadoAlerta estado);
}
