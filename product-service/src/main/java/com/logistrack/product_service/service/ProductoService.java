package com.logistrack.product_service.service;

import com.logistrack.product_service.dto.ProductoRequestDTO;
import com.logistrack.product_service.dto.ProductoResponseDTO;
import com.logistrack.product_service.model.Categoria;
import com.logistrack.product_service.model.Producto;
import com.logistrack.product_service.model.UnidadMedida;
import com.logistrack.product_service.repository.CategoriaRepository;
import com.logistrack.product_service.repository.UnidadMedidaRepository;
import com.logistrack.product_service.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    //convertidor de entidad a ResponseDTO
    private ProductoResponseDTO mapToDTO (Producto producto){
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getCategoria().getNombre(),
                producto.getUnidadMedida().getNombre(),
                producto.getPesoKg(),
                producto.getActivo()
        );
    }

    public List<ProductoResponseDTO> obtenerTodos(){
        log.info("Obteniendo todos los productos activos");
        return productoRepository.findByActivoTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO obtenerPorId(Long id) {
        log.info("Buscando producto con id: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id ));
                return mapToDTO(producto);
    }

    public ProductoResponseDTO obtenerPorSku(String sku){
        log.info("Buscando prodcuto con SKU: {}", sku);
        Producto producto = productoRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con sku: " + sku));
                return mapToDTO(producto);
    }

    public List<ProductoResponseDTO> buscarPorNombre(String nombre) {
        log.info("Buscnado productos con nombre: {}", nombre);
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        log.info("Creando producto con SKU: {}", dto.getSku());

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + dto.getCategoriaId()));

        UnidadMedida unidadMedida = unidadMedidaRepository.findById(dto.getUnidadMedidaId())
                .orElseThrow(() -> new RuntimeException("unidad de medida no encontrada cno id: " + dto.getUnidadMedidaId()));
        Producto producto = new Producto();
        producto.setSku(dto.getSku());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria);
        producto.setUnidadMedida(unidadMedida);
        producto.setPesoKg(dto.getPesoKg());
        producto.setActivo(true);
        return mapToDTO(productoRepository.save(producto));
    }

    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto){
        log.info("Actualizando producto con id: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Prodcuto no encontrado con id: " + id));
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + dto.getCategoriaId()));
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(dto.getUnidadMedidaId())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada con id: " + dto.getUnidadMedidaId()));
        producto.setSku(dto.getSku());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(categoria);
        producto.setUnidadMedida(unidadMedida);
        producto.setPesoKg(dto.getPesoKg());
        return mapToDTO(productoRepository.save(producto));
    }

    public void eliminar(Long id){
        log.info("Eliminando producto con id: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        producto.setActivo(false);
        productoRepository.save(producto);
    }

}
