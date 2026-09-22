package com.elmordisco.repositorio;

import com.elmordisco.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para gestionar los productos de El Mordisco.
 * Spring Data JPA proporciona automáticamente las operaciones CRUD.
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}