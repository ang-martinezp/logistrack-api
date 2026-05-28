package com.logistrack.notification_service.service;

import com.logistrack.notification_service.dto.NotificacionRequestDTO;
import com.logistrack.notification_service.dto.NotificacionResponseDTO;
import com.logistrack.notification_service.model.Notificacion;
import com.logistrack.notification_service.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<NotificacionResponseDTO> listarTodas() {
        log.info("Listando todas las notificaciones");
        return notificacionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public NotificacionResponseDTO obtenerPorId(Long id) {
        log.info("Buscando notificación con id: {}", id);
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con id: " + id));
        return mapToDTO(notificacion);
    }

    public List<NotificacionResponseDTO> obtenerPorDestinatario(String destinatario) {
        log.info("Buscando notificaciones para el destinatario: {}", destinatario);
        return notificacionRepository.findByDestinatario(destinatario)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public NotificacionResponseDTO crear(NotificacionRequestDTO dto) {
        log.info("Creando nueva notificación para el destinatario: {}", dto.getDestinatario());
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(dto.getTitulo());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setDestinatario(dto.getDestinatario());
        notificacion.setEstado(dto.getEstado());

        return mapToDTO(notificacionRepository.save(notificacion));
    }

    public NotificacionResponseDTO actualizarEstado(Long id, String nuevoEstado) {
        log.info("Actualizando estado de la notificación id: {} a {}", id, nuevoEstado);
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con id: " + id));
        notificacion.setEstado(nuevoEstado);
        return mapToDTO(notificacionRepository.save(notificacion));
    }

    private NotificacionResponseDTO mapToDTO(Notificacion notificacion) {
        return new NotificacionResponseDTO(
                notificacion.getId(),
                notificacion.getTitulo(),
                notificacion.getMensaje(),
                notificacion.getDestinatario(),
                notificacion.getEstado()
        );
    }
}
