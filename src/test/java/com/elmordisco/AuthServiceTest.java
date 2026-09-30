package com.elmordisco;

import com.elmordisco.modelo.Usuario;
import com.elmordisco.repositorio.UsuarioRepository;
import com.elmordisco.servicio.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuarioCorrectamente() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("ana");
        usuario.setContrasena("123456");

        when(usuarioRepository.findByUsuario("ana"))
                .thenReturn(null);

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        String resultado = authService.registrar(usuario);

        assertEquals("Usuario registrado correctamente", resultado);

        assertNotNull(usuario.getContrasena());
        assertNotNull(usuario.getContrasena());
        
        // BCrypt genera un hash diferente a la contraseña original.
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

assertEquals(
        true,
        encoder.matches("123456", usuario.getContrasena())
);

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void rechazarUsuarioExistente() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("ana");
        usuario.setContrasena("123456");

        when(usuarioRepository.findByUsuario("ana"))
                .thenReturn(usuario);

        String resultado = authService.registrar(usuario);

        assertEquals("El usuario ya existe", resultado);

        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void rechazarContrasenaCorta() {
        Usuario usuario = new Usuario();
        usuario.setUsuario("ana");
        usuario.setContrasena("123");

        String resultado = authService.registrar(usuario);

        assertEquals(
                "La contraseña debe tener mínimo 6 caracteres",
                resultado
        );

        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}