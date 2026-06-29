package com.logistrack.report_service.service;

import com.logistrack.report_service.dto.ReporteRequestDTO;
import com.logistrack.report_service.dto.ReporteResponseDTO;
import com.logistrack.report_service.model.Reporte;
import com.logistrack.report_service.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public List<ReporteResponseDTO> listarTodos() {
        log.info("Listando todos los reportes");
        return reporteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ReporteResponseDTO obtenerPorId(Long id) {
        log.info("Buscando reporte con id: {}", id);
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
        return mapToDTO(reporte);
    }

    public List<ReporteResponseDTO> obtenerPorTipo(String tipo) {
        log.info("Buscando reportes de tipo: {}", tipo);
        return reporteRepository.findByTipo(tipo)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ReporteResponseDTO crear(ReporteRequestDTO dto) {
        log.info("Creando nuevo reporte: {}", dto.getNombre());
        Reporte reporte = new Reporte();
        reporte.setNombre(dto.getNombre());
        reporte.setTipo(dto.getTipo());
        reporte.setContenido(dto.getContenido());
        reporte.setFechaGeneracion(LocalDateTime.now());

        return mapToDTO(reporteRepository.save(reporte));
    }

    public ReporteResponseDTO actualizar(Long id, ReporteRequestDTO dto) {
        log.info("Actualizando reporte id: {}", id);
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
        
        reporte.setNombre(dto.getNombre());
        reporte.setTipo(dto.getTipo());
        reporte.setContenido(dto.getContenido());

        return mapToDTO(reporteRepository.save(reporte));
    }

    private ReporteResponseDTO mapToDTO(Reporte reporte) {
        return new ReporteResponseDTO(
                reporte.getId(),
                reporte.getNombre(),
                reporte.getTipo(),
                reporte.getFechaGeneracion(),
                reporte.getContenido()
        );
    }
}
