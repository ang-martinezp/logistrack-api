package com.logistrack.auth_service.service;

import com.logistrack.auth_service.dto.LoginRequestDTO;
import com.logistrack.auth_service.dto.UsuarioRequestDTO;
import com.logistrack.auth_service.dto.UsuarioResponseDTO;
import com.logistrack.auth_service.model.Usuario;
import com.logistrack.auth_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> listarTodos() {
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO obtenerPorId(Long id) {
        log.info("Buscando usuario con id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return mapToDTO(usuario);
    }

    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        log.info("Creando nuevo usuario: {}", dto.getUsername());
        
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        usuario.setActivo(true);

        return mapToDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO actualizarEstado(Long id, Boolean activo) {
        log.info("Actualizando estado de activación del usuario id: {} a {}", id, activo);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuario.setActivo(activo);
        return mapToDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO login(LoginRequestDTO dto) {
        log.info("Intento de login para usuario: {}", dto.getUsername());
        Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        if (!usuario.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }
        return mapToDTO(usuario);
    }

    private UsuarioResponseDTO mapToDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getActivo()
        );
    }
}
