package com.elmordisco.controlador;

import com.elmordisco.modelo.Usuario;
import com.elmordisco.servicio.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador encargado de los servicios web
 * de registro e inicio de sesión.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor para inyectar el servicio de autenticación.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Servicio web para registrar un nuevo usuario.
     *
     * URL:
     * POST /api/auth/registro
     */
    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {

        String resultado = authService.registrar(usuario);

        // Si el usuario ya existe, devolvemos código 409.
        if (resultado.equals("El usuario ya existe")) {
            return ResponseEntity.status(409).body(resultado);
        }

        // Si hay algún error de validación, devolvemos código 400.
        if (!resultado.equals("Usuario registrado correctamente")) {
            return ResponseEntity.badRequest().body(resultado);
        }

        // Registro exitoso.
        return ResponseEntity.status(201).body(resultado);
    }

    /**
     * Servicio web para iniciar sesión.
     *
     * URL:
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(
            @RequestBody Usuario usuario) {

        String resultado = authService.iniciarSesion(
                usuario.getUsuario(),
                usuario.getContrasena()
        );

        // Si la autenticación es correcta.
        if (resultado.equals("Autenticación satisfactoria")) {
            return ResponseEntity.ok(resultado);
        }

        // Si las credenciales son incorrectas.
        if (resultado.equals("Error en la autenticación")) {
            return ResponseEntity.status(401).body(resultado);
        }

        // Error de validación.
        return ResponseEntity.badRequest().body(resultado);
    }
}