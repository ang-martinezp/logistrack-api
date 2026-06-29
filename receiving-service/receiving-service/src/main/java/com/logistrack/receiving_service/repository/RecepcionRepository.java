package com.logistrack.receiving_service.repository;

import com.logistrack.receiving_service.entity.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Long> {

    List<Recepcion> findByEstado(Recepcion.EstadoRecepcion estado);
    List<Recepcion> findByProveedorId(Long proveedorId);
    List<Recepcion> findByPedidoId(Long pedidoId);
}
