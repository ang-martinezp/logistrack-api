package com.logistrack.inventory_service.repository;

import com.logistrack.inventory_service.model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    List<MovimientoInventario> findByProductoId(Long productoId);
}
