package com.logistrack.order_service.repository;

import com.logistrack.order_service.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByProveedorId(Long proveedorId);
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
}


