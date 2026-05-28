package com.logistrack.product_service.service;

import com.logistrack.product_service.dto.CategoriaRequestDTO;
import com.logistrack.product_service.dto.CategoriaResponseDTO;
import com.logistrack.product_service.model.Categoria;
import com.logistrack.product_service.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    private CategoriaResponseDTO mapToDTO(Categoria categoria){
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getActivo()
        );
    }

    public List<CategoriaResponseDTO> obtenerTodas(){
        log.info("Obteniendo todas las cetegorias activas");
        return categoriaRepository.findByActivoTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO crear(CategoriaRequestDTO dto){
        log.info("Creando categoria: {}", dto.getNombre());
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setActivo(true);
        return mapToDTO(categoriaRepository.save(categoria));
    }

    public CategoriaResponseDTO actualizar(Long id, CategoriaRequestDTO dto){
        log.info("Actualizando categoria con id: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException ("Categoria no encontrada con id: " + id));
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return mapToDTO(categoriaRepository.save(categoria));
    }

    public void eliminar(Long id){
        log.info("Eliminando categoria con id: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no econtrada con id: " + id));
        categoria.setActivo(false);
        categoriaRepository.save(categoria);
    }

    public CategoriaResponseDTO obtenerPorId(Long id){
        log.info("Buscando categoría con ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoría no encontrada con id: " + id));
        return mapToDTO(categoria);
    }




}
