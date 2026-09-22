package com.elmordisco.controlador;

import com.elmordisco.modelo.Producto;
import com.elmordisco.servicio.ProductoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * Controlador REST para gestionar los productos de El Mordisco.
 * Permite consultar y registrar productos mediante solicitudes HTTP.
 */
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {
    
    private final ProductoService productoService;

    /**
     * Constructor del controlador.
     *
     * @param productoService servicio encargado de gestionar los productos
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos
     */
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    /**
     * Guarda un nuevo producto.
     *
     * @param producto datos del producto
     * @return producto guardado
     */
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }
    /**
 * Actualiza un producto existente.
 *
 * @param id identificador del producto
 * @param producto nuevos datos del producto
 * @return producto actualizado
 */
@PutMapping("/{id}")
public Producto actualizarProducto(
        @PathVariable Integer id,
        @RequestBody Producto producto) {

    producto.setIdProducto(id);
    return productoService.guardarProducto(producto);
}

/**
 * Elimina un producto por su identificador.
 *
 * @param id identificador del producto
 */
@DeleteMapping("/{id}")
public void eliminarProducto(@PathVariable Integer id) {
    productoService.eliminarProducto(id);
}
}