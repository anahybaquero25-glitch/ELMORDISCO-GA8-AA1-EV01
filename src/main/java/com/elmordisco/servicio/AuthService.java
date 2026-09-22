package com.elmordisco.servicio;

import com.elmordisco.modelo.Usuario;
import com.elmordisco.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio que contiene la lógica para registrar usuarios
 * y realizar la autenticación.
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    // Constructor para inyectar el repositorio.
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Registra un nuevo usuario.
     */
    public String registrar(Usuario usuario) {

        // Verificamos que el nombre de usuario no esté vacío.
        if (usuario.getUsuario() == null
                || usuario.getUsuario().trim().isEmpty()) {
            return "El usuario es obligatorio";
        }

        // Verificamos que la contraseña no esté vacía.
        if (usuario.getContrasena() == null
                || usuario.getContrasena().trim().isEmpty()) {
            return "La contraseña es obligatoria";
        }

        // Validamos que la contraseña tenga mínimo 6 caracteres.
        if (usuario.getContrasena().length() < 6) {
            return "La contraseña debe tener mínimo 6 caracteres";
        }

        // Verificamos si el usuario ya existe.
        Usuario usuarioExistente =
                usuarioRepository.findByUsuario(usuario.getUsuario());

        if (usuarioExistente != null) {
            return "El usuario ya existe";
        }

        // Guardamos el nuevo usuario en la base de datos.
        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }

    /**
     * Verifica las credenciales para iniciar sesión.
     */
    public String iniciarSesion(String nombreUsuario, String contrasena) {

        // Validamos que los datos no estén vacíos.
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()
                || contrasena == null || contrasena.trim().isEmpty()) {

            return "El usuario y la contraseña son obligatorios";
        }

        // Buscamos el usuario en la base de datos.
        Usuario usuario = usuarioRepository.findByUsuario(nombreUsuario);

        // Si no existe, la autenticación falla.
        if (usuario == null) {
            return "Error en la autenticación";
        }

        // Comparamos la contraseña ingresada con la almacenada.
        if (!usuario.getContrasena().equals(contrasena)) {
            return "Error en la autenticación";
        }

        return "Autenticación satisfactoria";
    }
}