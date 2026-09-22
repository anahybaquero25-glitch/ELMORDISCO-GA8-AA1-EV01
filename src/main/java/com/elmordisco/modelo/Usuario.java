package com.elmordisco.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Modelo que representa a un usuario del sistema.
 * Esta clase se relaciona con la tabla usuario de la base de datos.
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    // Identificador único del usuario.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre de usuario utilizado para iniciar sesión.
    private String usuario;

    // Contraseña utilizada para la autenticación.
    private String contrasena;

    // Constructor vacío requerido por JPA.
    public Usuario() {
    }

    // Constructor para crear un usuario.
    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}