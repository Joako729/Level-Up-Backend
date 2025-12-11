package com.Level_Up.Mobile.backend_gamer.Producto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productos")
class ProductoController(private val repository: ProductoRepository) {

    @GetMapping
    fun obtenerTodosLosProductos(): List<Producto> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun obtenerProductoPorId(@PathVariable id: Long): ResponseEntity<Producto> {
        val producto = repository.findById(id)
        return if (producto.isPresent) {
            ResponseEntity.ok(producto.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun crearNuevoProducto(@RequestBody nuevoProducto: Producto): ResponseEntity<Producto> {
        val productoGuardado = repository.save(nuevoProducto)
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado)
    }

    @PutMapping("/{id}")
    fun actualizarProducto(@PathVariable id: Long, @RequestBody productoActualizado: Producto): ResponseEntity<Producto> {
        return if (repository.existsById(id)) {
            val productoAGuardar = productoActualizado.copy(id = id)
            val productoGuardado = repository.save(productoAGuardar)
            ResponseEntity.ok(productoGuardado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarProducto(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}