package com.logistrack.auth_service.controller;

import com.logistrack.auth_service.dto.LoginRequestDTO;
import com.logistrack.auth_service.dto.LoginResponseDTO;
import com.logistrack.auth_service.dto.UsuarioRequestDTO;
import com.logistrack.auth_service.dto.UsuarioResponseDTO;
import com.logistrack.auth_service.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login_cuandoCredencialesSonValidas_deberiaRetornarOkYToken() throws Exception {
        // Given
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(1L, "admin", "admin@logistrack.com", "ADMIN", true);
        LoginResponseDTO respuesta = new LoginResponseDTO("mocked-jwt-token", usuarioDTO);

        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("1234");

        when(authService.login(any(LoginRequestDTO.class))).thenReturn(respuesta);

        // When & Then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"))
                .andExpect(jsonPath("$.usuario.username").value("admin"))
                .andExpect(jsonPath("$.usuario.rol").value("ADMIN"));
    }

    @Test
    void login_cuandoCredencialesEstanVacias_deberiaRetornarBadRequest() throws Exception {
        // Given
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("");
        request.setPassword("");

        // When & Then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listarTodos_deberiaRetornarListaDeUsuarios() throws Exception {
        // Given
        UsuarioResponseDTO u1 = new UsuarioResponseDTO(1L, "u1", "u1@test.com", "USER", true);
        UsuarioResponseDTO u2 = new UsuarioResponseDTO(2L, "u2", "u2@test.com", "ADMIN", true);

        when(authService.listarTodos()).thenReturn(List.of(u1, u2));

        // When & Then
        mockMvc.perform(get("/api/auth"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("u1"))
                .andExpect(jsonPath("$[1].username").value("u2"));
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarUsuario() throws Exception {
        // Given
        UsuarioResponseDTO usuario = new UsuarioResponseDTO(1L, "user1", "u1@test.com", "USER", true);

        when(authService.obtenerPorId(1L)).thenReturn(usuario);

        // When & Then
        mockMvc.perform(get("/api/auth/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user1"));
    }

    @Test
    void registro_cuandoDatosValidos_deberiaRetornarCreated() throws Exception {
        // Given
        UsuarioRequestDTO request = new UsuarioRequestDTO("newUser", "1234", "new@test.com", "USER");
        UsuarioResponseDTO respuesta = new UsuarioResponseDTO(10L, "newUser", "new@test.com", "USER", true);

        when(authService.crear(any(UsuarioRequestDTO.class))).thenReturn(respuesta);

        // When & Then
        mockMvc.perform(post("/api/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.authId").value(10))
                .andExpect(jsonPath("$.username").value("newUser"));
    }

    @Test
    void actualizarEstado_deberiaRetornarOk() throws Exception {
        // Given
        UsuarioResponseDTO respuesta = new UsuarioResponseDTO(1L, "user1", "u1@test.com", "USER", false);

        when(authService.actualizarEstado(eq(1L), eq(false))).thenReturn(respuesta);

        // When & Then
        mockMvc.perform(patch("/api/auth/{id}/estado", 1L)
                        .param("activo", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activo").value(false));
    }
}
