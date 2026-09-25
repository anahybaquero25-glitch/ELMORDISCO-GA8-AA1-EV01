# DOCUMENTACIÓN DE LA API REST - EL MORDISCO

## 1. Información general

**Proyecto:** El Mordisco
**Tecnología:** Spring Boot
**Lenguaje:** Java 21
**Base de datos:** MySQL
**Servidor:** Apache Tomcat
**Puerto:** 8081

La API REST de El Mordisco permite gestionar usuarios y productos mediante servicios web HTTP. La aplicación utiliza Spring Boot, Spring Data JPA y MySQL para procesar las solicitudes y almacenar la información.

**URL base:**

`http://localhost:8081`

---

## 2. Servicio de registro de usuarios

### Descripción

Permite registrar un nuevo usuario en el sistema.

### Método HTTP

`POST`

### URL

`http://localhost:8081/api/auth/registro`

### Cuerpo de la solicitud

```json
{
    "usuario": "anahi",
    "contrasena": "123456"
}
```

### Respuesta exitosa

**Código HTTP:** `201 Created`

```text
Usuario registrado correctamente
```

### Respuestas de error

**Código HTTP:** `409 Conflict`

```text
El usuario ya existe
```

**Código HTTP:** `400 Bad Request`

Se genera cuando los datos enviados no cumplen las validaciones establecidas para el registro.

---

## 3. Servicio de inicio de sesión

### Descripción

Permite verificar las credenciales de un usuario registrado.

### Método HTTP

`POST`

### URL

`http://localhost:8081/api/auth/login`

### Cuerpo de la solicitud

```json
{
    "usuario": "anahi",
    "contrasena": "123456"
}
```

### Respuesta exitosa

**Código HTTP:** `200 OK`

```text
Autenticación satisfactoria
```

### Respuesta cuando las credenciales son incorrectas

**Código HTTP:** `401 Unauthorized`

```text
Error en la autenticación
```

### Respuesta por datos inválidos

**Código HTTP:** `400 Bad Request`

Se genera cuando los datos enviados presentan algún error de validación.

---

# 4. Servicios de productos

La API de productos permite consultar, registrar, actualizar y eliminar productos de El Mordisco.

## 4.1 Consultar productos

### Descripción

Obtiene la lista de productos registrados en la base de datos.

### Método HTTP

`GET`

### URL

`http://localhost:8081/api/productos`

### Respuesta exitosa

**Código HTTP:** `200 OK`

Ejemplo:

```json
[
    {
        "idProducto": 1,
        "nombre": "Hamburguesa Especial",
        "categoria": "Hamburguesas",
        "precio": 20000.0,
        "stock": 15
    }
]
```

---

## 4.2 Registrar un producto

### Descripción

Permite crear y guardar un nuevo producto.

### Método HTTP

`POST`

### URL

`http://localhost:8081/api/productos`

### Cuerpo de la solicitud

```json
{
    "nombre": "Hamburguesa Especial",
    "categoria": "Hamburguesas",
    "precio": 18000,
    "stock": 20
}
```

### Validaciones

El servicio verifica que:

* El nombre sea obligatorio.
* La categoría sea obligatoria.
* El precio sea obligatorio.
* El precio sea mayor que cero.
* El stock sea obligatorio.
* El stock no sea negativo.

### Respuesta exitosa

El servicio devuelve el producto registrado junto con su identificador.

**Código HTTP esperado:** `200 OK`

### Respuesta de validación

**Código HTTP:** `400 Bad Request`

Se genera cuando uno o más datos del producto no cumplen las reglas de validación.

Ejemplo de datos inválidos:

```json
{
    "nombre": "",
    "categoria": "",
    "precio": -5000,
    "stock": -10
}
```

Resultado:

```text
400 Bad Request
```

---

## 4.3 Actualizar un producto

### Descripción

Permite modificar la información de un producto existente.

### Método HTTP

`PUT`

### URL

`http://localhost:8081/api/productos/{id}`

Ejemplo:

`http://localhost:8081/api/productos/1`

### Cuerpo de la solicitud

```json
{
    "nombre": "Hamburguesa Especial Actualizada",
    "categoria": "Hamburguesas",
    "precio": 20000,
    "stock": 15
}
```

### Validaciones

La actualización utiliza las mismas validaciones establecidas para el registro de productos:

* Nombre obligatorio.
* Categoría obligatoria.
* Precio obligatorio.
* Precio mayor que cero.
* Stock obligatorio.
* Stock igual o mayor que cero.

### Respuesta exitosa

Devuelve el producto actualizado.

**Código HTTP esperado:** `200 OK`

---

## 4.4 Eliminar un producto

### Descripción

Permite eliminar un producto utilizando su identificador.

### Método HTTP

`DELETE`

### URL

`http://localhost:8081/api/productos/{id}`

Ejemplo:

`http://localhost:8081/api/productos/1`

### Respuesta exitosa

El producto es eliminado de la base de datos.

**Código HTTP:** `200 OK`

---

# 5. Resumen de servicios

| Servicio            | Método | URL                   | Función             |
| ------------------- | ------ | --------------------- | ------------------- |
| Registro            | POST   | `/api/auth/registro`  | Registrar usuarios  |
| Inicio de sesión    | POST   | `/api/auth/login`     | Autenticar usuarios |
| Listar productos    | GET    | `/api/productos`      | Consultar productos |
| Registrar producto  | POST   | `/api/productos`      | Crear productos     |
| Actualizar producto | PUT    | `/api/productos/{id}` | Modificar productos |
| Eliminar producto   | DELETE | `/api/productos/{id}` | Eliminar productos  |

---

# 6. Validaciones de la API

La API implementa validaciones para evitar el ingreso de información incorrecta.

En los productos se utilizan las siguientes reglas:

* El nombre no puede estar vacío.
* La categoría no puede estar vacía.
* El precio es obligatorio y debe ser mayor que cero.
* El stock es obligatorio y no puede ser negativo.

Cuando una solicitud no cumple las reglas establecidas, la API responde con:

`400 Bad Request`

Estas validaciones permiten verificar que la información recibida por los servicios sea correcta antes de almacenarla en la base de datos.

---

# 7. Pruebas realizadas

Los servicios fueron probados utilizando Postman.

Se realizaron pruebas para:

1. Consultar productos mediante `GET`.
2. Registrar productos mediante `POST`.
3. Actualizar productos mediante `PUT`.
4. Eliminar productos mediante `DELETE`.
5. Consultar nuevamente los productos después de la eliminación.
6. Registrar usuarios mediante el servicio de registro.
7. Probar el inicio de sesión.
8. Enviar datos inválidos para comprobar las validaciones.

La prueba de validación de productos fue realizada enviando datos incorrectos:

```json
{
    "nombre": "",
    "categoria": "",
    "precio": -5000,
    "stock": -10
}
```

La API respondió:

`400 Bad Request`

Esto demuestra que el servicio rechaza información que no cumple las reglas de validación.

---

# 8. Arquitectura del proyecto

El proyecto está organizado utilizando diferentes capas:

* **Modelo:** contiene las entidades que representan los datos del sistema.
* **Repositorio:** permite acceder a la información almacenada en MySQL mediante Spring Data JPA.
* **Servicio:** contiene la lógica de negocio.
* **Controlador:** expone los servicios REST mediante endpoints HTTP.

Esta organización permite mantener separado el acceso a datos, la lógica de negocio y la exposición de los servicios web.

---

# 9. Herramienta de versionamiento

El proyecto utiliza **Git** como herramienta de control de versiones.

El código fuente se encuentra asociado a un repositorio remoto de GitHub, donde se almacenan los cambios realizados durante el desarrollo del proyecto.

El enlace del repositorio se encuentra en el archivo:

`enlaceRepositorio.txt`

