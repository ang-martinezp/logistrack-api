package com.logistrack.auth_service.service;

import com.logistrack.auth_service.dto.LoginRequestDTO;
import com.logistrack.auth_service.dto.LoginResponseDTO;
import com.logistrack.auth_service.dto.UsuarioRequestDTO;
import com.logistrack.auth_service.dto.UsuarioResponseDTO;
import com.logistrack.auth_service.model.Usuario;
import com.logistrack.auth_service.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void login_cuandoCredencialesSonValidas_deberiaRetornarTokenYUsuario() {
        // Given
        Usuario usuario = new Usuario(1L, "admin", "1234", "admin@logistrack.com", "ADMIN", true);
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("1234");

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuario));

        // When
        LoginResponseDTO respuesta = authService.login(request);

        // Then
        assertThat(respuesta).isNotNull();
        assertThat(respuesta.getToken()).isNotBlank();
        assertThat(respuesta.getUsuario().getUsername()).isEqualTo("admin");
        assertThat(respuesta.getUsuario().getRol()).isEqualTo("ADMIN");
        verify(usuarioRepository).findByUsername("admin");
    }

    @Test
    void login_cuandoPasswordIncorrecto_deberiaLanzarExcepcion() {
        // Given
        Usuario usuario = new Usuario(1L, "admin", "1234", "admin@logistrack.com", "ADMIN", true);
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("wrong_password");

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuario));

        // When & Then
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Credenciales inválidas");
    }

    @Test
    void login_cuandoUsuarioInactivo_deberiaLanzarExcepcion() {
        // Given
        Usuario usuario = new Usuario(1L, "admin", "1234", "admin@logistrack.com", "ADMIN", false);
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("1234");

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuario));

        // When & Then
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario inactivo");
    }

    @Test
    void crear_cuandoUsuarioYaExiste_deberiaLanzarExcepcion() {
        // Given
        UsuarioRequestDTO request = new UsuarioRequestDTO("admin", "1234", "admin@logistrack.com", "ADMIN");
        Usuario usuarioExistente = new Usuario(1L, "admin", "1234", "admin@logistrack.com", "ADMIN", true);

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuarioExistente));

        // When & Then
        assertThatThrownBy(() -> authService.crear(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("El nombre de usuario ya está registrado");

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void crear_cuandoUsuarioNoExiste_deberiaGuardarCorrectamente() {
        // Given
        UsuarioRequestDTO request = new UsuarioRequestDTO("newUser", "1234", "new@logistrack.com", "USER");
        Usuario usuarioGuardado = new Usuario(2L, "newUser", "1234", "new@logistrack.com", "USER", true);

        when(usuarioRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        // When
        UsuarioResponseDTO respuesta = authService.crear(request);

        // Then
        assertThat(respuesta).isNotNull();
        assertThat(respuesta.getUsername()).isEqualTo("newUser");
        assertThat(respuesta.getEmail()).isEqualTo("new@logistrack.com");
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void listarTodos_deberiaRetornarListaDeUsuarios() {
        // Given
        Usuario u1 = new Usuario(1L, "u1", "p1", "u1@test.com", "USER", true);
        Usuario u2 = new Usuario(2L, "u2", "p2", "u2@test.com", "ADMIN", true);

        when(usuarioRepository.findAll()).thenReturn(List.of(u1, u2));

        // When
        List<UsuarioResponseDTO> respuesta = authService.listarTodos();

        // Then
        assertThat(respuesta).hasSize(2);
        assertThat(respuesta.get(0).getUsername()).isEqualTo("u1");
        assertThat(respuesta.get(1).getUsername()).isEqualTo("u2");
        verify(usuarioRepository).findAll();
    }

    @Test
    void obtenerPorId_cuandoUsuarioExiste_deberiaRetornarUsuario() {
        // Given
        Usuario usuario = new Usuario(1L, "user1", "p1", "u1@test.com", "USER", true);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // When
        UsuarioResponseDTO respuesta = authService.obtenerPorId(1L);

        // Then
        assertThat(respuesta).isNotNull();
        assertThat(respuesta.getUsername()).isEqualTo("user1");
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoUsuarioNoExiste_deberiaLanzarExcepcion() {
        // Given
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> authService.obtenerPorId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario no encontrado con id: 99");
    }

    @Test
    void actualizarEstado_cuandoUsuarioExiste_deberiaActualizarYRetornarUsuario() {
        // Given
        Usuario usuario = new Usuario(1L, "user1", "p1", "u1@test.com", "USER", true);
        Usuario usuarioModificado = new Usuario(1L, "user1", "p1", "u1@test.com", "USER", false);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioModificado);

        // When
        UsuarioResponseDTO respuesta = authService.actualizarEstado(1L, false);

        // Then
        assertThat(respuesta).isNotNull();
        assertThat(respuesta.getActivo()).isFalse();
        verify(usuarioRepository).findById(1L);
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
