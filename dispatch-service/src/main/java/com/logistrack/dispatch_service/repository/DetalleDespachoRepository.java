package com.logistrack.dispatch_service.repository;

import com.logistrack.dispatch_service.entity.DetalleDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDespachoRepository extends JpaRepository<DetalleDespacho, Long >{

    List<DetalleDespacho> findByDespachoId(Long despachoId);
}
