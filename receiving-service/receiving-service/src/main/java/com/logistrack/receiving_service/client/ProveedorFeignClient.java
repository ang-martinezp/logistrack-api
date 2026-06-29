package com.logistrack.receiving_service.client;

import com.logistrack.receiving_service.dto.ProveedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "supplier-service")
public interface ProveedorFeignClient {

    @GetMapping("/api/proveedores/{id}")
    ProveedorDTO obtenerProveedorPorId(@PathVariable("id") Long id);
}