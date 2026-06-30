package com.logistrack.product_service.controller;

import com.logistrack.product_service.dto.ProductoRequestDTO;
import com.logistrack.product_service.dto.ProductoResponseDTO;
import com.logistrack.product_service.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarTodos_deberiaRetornarListaDeProductos() throws Exception {
        // Given
        ProductoResponseDTO p1 = new ProductoResponseDTO(1L, "PROD-001", "Leche", "Leche entera", "Lácteos", "Litro", new BigDecimal("1.000"), true);
        ProductoResponseDTO p2 = new ProductoResponseDTO(2L, "PROD-002", "Yogurt", "Yogurt de frutilla", "Lácteos", "Litro", new BigDecimal("0.125"), true);

        when(productoService.obtenerTodos()).thenReturn(List.of(p1, p2));

        // When & Then
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].sku").value("PROD-001"))
                .andExpect(jsonPath("$[1].sku").value("PROD-002"));
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarOk() throws Exception {
        // Given
        ProductoResponseDTO producto = new ProductoResponseDTO(1L, "PROD-001", "Leche", "Leche entera", "Lácteos", "Litro", new BigDecimal("1.000"), true);

        when(productoService.obtenerPorId(1L)).thenReturn(producto);

        // When & Then
        mockMvc.perform(get("/api/productos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Leche"))
                .andExpect(jsonPath("$.categoriaNombre").value("Lácteos"));
    }

    @Test
    void crear_cuandoDatosValidos_deberiaRetornarCreated() throws Exception {
        // Given
        ProductoRequestDTO request = new ProductoRequestDTO("PROD-100", "Queso", "Queso laminado", 1L, 1L, new BigDecimal("0.250"));
        ProductoResponseDTO respuesta = new ProductoResponseDTO(10L, "PROD-100", "Queso", "Queso laminado", "Lácteos", "Kilogramo", new BigDecimal("0.250"), true);

        when(productoService.crear(any(ProductoRequestDTO.class))).thenReturn(respuesta);

        // When & Then
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.sku").value("PROD-100"));
    }

    @Test
    void actualizar_deberiaRetornarOk() throws Exception {
        // Given
        ProductoRequestDTO request = new ProductoRequestDTO("PROD-100", "Queso", "Queso mantecoso", 1L, 1L, new BigDecimal("0.250"));
        ProductoResponseDTO respuesta = new ProductoResponseDTO(10L, "PROD-100", "Queso mantecoso", "Queso mantecoso", "Lácteos", "Kilogramo", new BigDecimal("0.250"), true);

        when(productoService.actualizar(eq(10L), any(ProductoRequestDTO.class))).thenReturn(respuesta);

        // When & Then
        mockMvc.perform(put("/api/productos/{id}", 10L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Queso mantecoso"));
    }
}
