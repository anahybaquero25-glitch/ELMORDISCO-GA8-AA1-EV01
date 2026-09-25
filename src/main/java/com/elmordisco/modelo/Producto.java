package com.elmordisco.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Entidad que representa un producto de El Mordisco.
 * Se conecta con la tabla producto de MySQL.
 */
@Entity
@Table(name = "producto")
public class Producto {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_producto")
private Integer idProducto;

    @NotBlank(message = "El nombre es obligatorio")
private String nombre;

@NotBlank(message = "La categoria es obligatoria")
private String categoria;

@NotNull(message = "El precio es obligatorio")
@Positive(message = "El precio debe ser mayor que cero")
private Double precio;

@NotNull(message = "El stock es obligatorio")
@Min(value = 0, message = "El stock no puede ser negativo")
private Integer stock;

    // Constructor vacío requerido por JPA
    public Producto() {
    }

    // Constructor para crear productos
    public Producto(String nombre, String categoria, Double precio, Integer stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
