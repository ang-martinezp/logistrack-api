package com.logistrack.supplier_service.service;

import com.logistrack.supplier_service.dto.ProveedorRequestDTO;
import com.logistrack.supplier_service.dto.ProveedorResponseDTO;
import com.logistrack.supplier_service.model.Proveedor;
import com.logistrack.supplier_service.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public List<ProveedorResponseDTO> listarTodos() {
        log.info("Listando todos los proveedores");
        return proveedorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProveedorResponseDTO obtenerPorId(Long id){
        log.info("Buscando proveedor con id: {}", id);
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Proveedor no encontrado con el ID: " + id));
            return mapToDTO(proveedor);
    }

    public ProveedorResponseDTO obtenerPorRut(String rut){
        log.info("Buscando proveedor con rut: {}", rut);
        Proveedor proveedor = proveedorRepository.findByRut(rut)
                .orElseThrow(()-> new RuntimeException("Proveedor no encontrado con rut: " + rut));
            return mapToDTO(proveedor);
    }

    public List<ProveedorResponseDTO> buscarPorNombre(String nombre){
        log.info("Buscando proveedor con nombre: {}",nombre);
        return proveedorRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ProveedorResponseDTO> obtenerPorEstado(Proveedor.EstadoProveedor estado){
        log.info("Bucando proveedor con estado: {}", estado);
        return proveedorRepository.findByEstado(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProveedorResponseDTO crear(ProveedorRequestDTO dto){
        log.info("Creando proveedor: {}", dto.getNombre());
        proveedorRepository.findByRut(dto.getRut()).ifPresent(p ->{
            throw new RuntimeException("Ya existe un proveedor con el RUT: " + dto.getRut());
        });
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setRut(dto.getRut());
        proveedor.setEmail(dto.getEmail());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setContacto(dto.getContacto());
        proveedor.setEstado(Proveedor.EstadoProveedor.ACTIVO);
        return mapToDTO(proveedorRepository.save(proveedor));
    }

    public ProveedorResponseDTO actualizar (Long id, ProveedorRequestDTO dto){
        log.info("Actualizando proveedor con id: {}", id);
        Proveedor proveedor =  proveedorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Proveedor no encontrado con id: " + id));
        proveedor.setNombre(dto.getNombre());
        proveedor.setEmail(dto.getEmail());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setContacto(dto.getContacto());
        return mapToDTO(proveedorRepository.save(proveedor));
    }

    public ProveedorResponseDTO cambiarEstado(Long id, Proveedor.EstadoProveedor nuevoEstado) {
        log.info("Cambiando estado del proveedor {} a estado {}", id, nuevoEstado);
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Proveedor no encontrado con id: " +  id));
        proveedor.setEstado(nuevoEstado);
        return mapToDTO(proveedorRepository.save(proveedor));
    }

    private ProveedorResponseDTO mapToDTO(Proveedor proveedor){
        return new ProveedorResponseDTO(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getRut(),
                proveedor.getEmail(),
                proveedor.getTelefono(),
                proveedor.getDireccion(),
                proveedor.getContacto(),
                proveedor.getEstado().name());
    }
}

