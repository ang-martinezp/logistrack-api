package com.logistrack.product_service.service;

import com.logistrack.product_service.dto.ProductoRequestDTO;
import com.logistrack.product_service.dto.ProductoResponseDTO;
import com.logistrack.product_service.model.Categoria;
import com.logistrack.product_service.model.Producto;
import com.logistrack.product_service.model.UnidadMedida;
import com.logistrack.product_service.repository.CategoriaRepository;
import com.logistrack.product_service.repository.ProductoRepository;
import com.logistrack.product_service.repository.UnidadMedidaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private UnidadMedidaRepository unidadMedidaRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void obtenerTodos_deberiaRetornarProductosActivos() {
        // Given
        Categoria cat = new Categoria(1L, "Lácteos", "Productos lácteos", true);
        UnidadMedida um = new UnidadMedida(1L, "Litro", "L");
        Producto p1 = new Producto(1L, "PROD-001", "Leche", "Leche entera", cat, um, new BigDecimal("1.000"), true);
        Producto p2 = new Producto(2L, "PROD-002", "Yogurt", "Yogurt de frutilla", cat, um, new BigDecimal("0.125"), true);

        when(productoRepository.findByActivoTrue()).thenReturn(List.of(p1, p2));

        // When
        List<ProductoResponseDTO> resultado = productoService.obtenerTodos();

        // Then
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getSku()).isEqualTo("PROD-001");
        assertThat(resultado.get(1).getSku()).isEqualTo("PROD-002");
        verify(productoRepository).findByActivoTrue();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarProducto() {
        // Given
        Categoria cat = new Categoria(1L, "Lácteos", "Productos lácteos", true);
        UnidadMedida um = new UnidadMedida(1L, "Litro", "L");
        Producto producto = new Producto(1L, "PROD-001", "Leche", "Leche entera", cat, um, new BigDecimal("1.000"), true);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // When
        ProductoResponseDTO resultado = productoService.obtenerPorId(1L);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Leche");
        verify(productoRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        // Given
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> productoService.obtenerPorId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Producto no encontrado con id: 99");
    }

    @Test
    void obtenerPorSku_cuandoExiste_deberiaRetornarProducto() {
        // Given
        Categoria cat = new Categoria(1L, "Lácteos", "Productos lácteos", true);
        UnidadMedida um = new UnidadMedida(1L, "Litro", "L");
        Producto producto = new Producto(1L, "PROD-001", "Leche", "Leche entera", cat, um, new BigDecimal("1.000"), true);

        when(productoRepository.findBySku("PROD-001")).thenReturn(Optional.of(producto));

        // When
        ProductoResponseDTO resultado = productoService.obtenerPorSku("PROD-001");

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getSku()).isEqualTo("PROD-001");
        verify(productoRepository).findBySku("PROD-001");
    }

    @Test
    void obtenerPorSku_cuandoNoExiste_deberiaLanzarExcepcion() {
        // Given
        when(productoRepository.findBySku("PROD-999")).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> productoService.obtenerPorSku("PROD-999"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Producto no encontrado con sku: PROD-999");
    }

    @Test
    void crear_cuandoDatosValidos_deberiaGuardarProducto() {
        // Given
        ProductoRequestDTO request = new ProductoRequestDTO("PROD-100", "Queso", "Queso laminado", 1L, 1L, new BigDecimal("0.250"));
        Categoria cat = new Categoria(1L, "Lácteos", "Productos lácteos", true);
        UnidadMedida um = new UnidadMedida(1L, "Kilogramo", "KG");
        Producto productoGuardado = new Producto(10L, "PROD-100", "Queso", "Queso laminado", cat, um, new BigDecimal("0.250"), true);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(cat));
        when(unidadMedidaRepository.findById(1L)).thenReturn(Optional.of(um));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        // When
        ProductoResponseDTO resultado = productoService.crear(request);

        // Then
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getNombre()).isEqualTo("Queso");
        verify(productoRepository).save(any(Producto.class));
    }
}
