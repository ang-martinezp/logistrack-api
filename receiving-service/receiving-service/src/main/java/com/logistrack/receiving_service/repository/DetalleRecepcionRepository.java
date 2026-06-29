package com.logistrack.receiving_service.repository;

import com.logistrack.receiving_service.entity.DetalleRecepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleRecepcionRepository extends JpaRepository<DetalleRecepcion, Long>{

    List<DetalleRecepcion> findByRecepcionId(Long recepcionId);
}
