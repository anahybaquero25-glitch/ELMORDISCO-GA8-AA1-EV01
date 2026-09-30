package com.elmordisco;

import com.elmordisco.modelo.Producto;
import com.elmordisco.repositorio.ProductoRepository;
import com.elmordisco.servicio.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductos() {
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();

        List<Producto> productos = Arrays.asList(producto1, producto2);

        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoService.listarProductos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(productoRepository).findAll();
    }

    @Test
    void guardarProducto() {
        Producto producto = new Producto();

        when(productoRepository.save(producto))
                .thenReturn(producto);

        Producto resultado = productoService.guardarProducto(producto);

        assertNotNull(resultado);
        assertEquals(producto, resultado);

        verify(productoRepository).save(producto);
    }

    @Test
    void buscarProducto() {
        Producto producto = new Producto();

        when(productoRepository.findById(1))
                .thenReturn(Optional.of(producto));

        Optional<Producto> resultado =
                productoService.buscarProducto(1);

        assertNotNull(resultado);
        assertEquals(producto, resultado.orElse(null));

        verify(productoRepository).findById(1);
    }

    @Test
    void eliminarProducto() {

        productoService.eliminarProducto(1);

        verify(productoRepository).deleteById(1);
    }
}