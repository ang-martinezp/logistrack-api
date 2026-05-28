package com.logistrack.inventory_service.repository;

import com.logistrack.inventory_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long>{
    Optional<Stock> findByProductoId(Long productoId);
}


