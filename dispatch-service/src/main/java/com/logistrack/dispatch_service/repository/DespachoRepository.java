package com.logistrack.dispatch_service.repository;


import com.logistrack.dispatch_service.entity.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DespachoRepository  extends JpaRepository<Despacho, Long> {

    List<Despacho> findByEstado(Despacho.EstadoDespacho estado);
    List<Despacho> findByPedidoId(Long pedidoId);
}
