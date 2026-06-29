package com.logistrack.user_service.service;

import com.logistrack.user_service.dto.UsuarioRequestDTO;
import com.logistrack.user_service.dto.UsuarioResponseDTO;
import com.logistrack.user_service.model.Usuario;
import com.logistrack.user_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> listarTodos(){
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obtenerPorId(Long id){
        log.info("Buscando usuario con id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: "+ id));
        return mapToDTO(usuario);
    }

    public UsuarioResponseDTO obtenerPorUsername(String username) {
        log.info("Buscando usuario con username {}", username);
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para el username: "+ username));
                return mapToDTO(usuario);
    }

    public List<UsuarioResponseDTO> obtenerPorRol(Usuario.RolUsuario rol){
        log.info("Buscando usuarios con el rol: {}", rol);
        return usuarioRepository.findByRol(rol)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> obtenerPorEstado(Usuario.EstadoUsuario estado){
        log.info("Buscando usuarios con estado: {}", estado);
        return usuarioRepository.findByEstado(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO crear(UsuarioRequestDTO dto){
        log.info("Crando usuario: {}", dto.getUsername());
        usuarioRepository.findByUsername(dto.getUsername()).ifPresent(u ->{
            throw new RuntimeException("Ya existe un usuario con el username: " + dto.getUsername());
            });
        usuarioRepository.findByEmail(dto.getEmail()).ifPresent(u ->{
            throw new RuntimeException("Ya existe un usuario con el email: "+ dto.getEmail());
            });
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(Usuario.RolUsuario.valueOf(dto.getRol()));
        return mapToDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO dto){
        log.info("Actualizando usuario con id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado con id: " + id));
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(Usuario.RolUsuario.valueOf(dto.getRol()));
        return mapToDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO cambiarEstado(Long id, Usuario.EstadoUsuario nuevoEstado) {
        log.info("Cambiando estado para el usuario con id: {} a {}", id, nuevoEstado);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con id: "+ id));
        usuario.setEstado(nuevoEstado);
        return mapToDTO(usuarioRepository.save(usuario));
    }

    private UsuarioResponseDTO mapToDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getRol().name(),
                usuario.getEstado().name()
        );
    }
}
