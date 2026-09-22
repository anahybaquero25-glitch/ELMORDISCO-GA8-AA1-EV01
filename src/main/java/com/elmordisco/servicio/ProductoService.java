package com.elmordisco.servicio;

import com.elmordisco.modelo.Producto;
import com.elmordisco.repositorio.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para gestionar los productos.
 * Contiene la lógica de negocio de El Mordisco.
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor que recibe el repositorio de productos.
     *
     * @param productoRepository repositorio para acceder a los datos
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * @return lista de productos
     */
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    /**
     * Guarda un producto en la base de datos.
     *
     * @param producto producto que se desea guardar
     * @return producto guardado
     */
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Busca un producto por su identificador.
     *
     * @param id identificador del producto
     * @return producto encontrado, si existe
     */
    public Optional<Producto> buscarProducto(Integer id) {
        return productoRepository.findById(id);
    }

    /**
     * Elimina un producto por su identificador.
     *
     * @param id identificador del producto
     */
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}