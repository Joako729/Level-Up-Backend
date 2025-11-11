package com.Level_Up.Mobile.backend_gamer.Producto

import org.springframework.web.bind.annotation.*

@RestController // Indica que esta clase recibe peticiones web
@RequestMapping("/api/productos") // Define la ruta URL base para esta clase
class ProductoController(private val repository: ProductoRepository) {

    // Maneja peticiones GET a: http://localhost:8080/api/productos
    @GetMapping
    fun obtenerTodosLosProductos(): List<Producto> {
        // Llama al repositorio para obtener todos los datos de la BD de AWS
        return repository.findAll()
    }

    // Maneja peticiones POST a: http://localhost:8080/api/productos (para crear un producto)
    @PostMapping
    fun crearNuevoProducto(@RequestBody nuevoProducto: Producto): Producto {
        return repository.save(nuevoProducto)
    }
}