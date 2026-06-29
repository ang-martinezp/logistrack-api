package com.logistrack.supplier_service.repository;

import com.logistrack.supplier_service.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByEstado(Proveedor.EstadoProveedor estado);
    Optional<Proveedor> findByRut(String rut);
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
}

