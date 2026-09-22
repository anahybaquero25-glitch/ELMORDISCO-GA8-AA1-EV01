package com.elmordisco.repositorio;

import com.elmordisco.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio encargado de realizar las operaciones
 * de persistencia de los usuarios.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Busca un usuario por su nombre de usuario.
    Usuario findByUsuario(String usuario);
}