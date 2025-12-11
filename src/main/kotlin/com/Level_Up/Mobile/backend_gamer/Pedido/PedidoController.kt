package com.Level_Up.Mobile.backend_gamer.Pedido

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pedidos")
class PedidoController(private val repository: PedidoRepository) {

    @GetMapping
    fun obtenerTodosLosPedidos(): List<Pedido> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun obtenerPedidoPorId(@PathVariable id: Long): ResponseEntity<Pedido> {
        val pedido = repository.findById(id)
        return if (pedido.isPresent) {
            ResponseEntity.ok(pedido.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun crearNuevoPedido(@RequestBody nuevoPedido: Pedido): ResponseEntity<Pedido> {
        val pedidoGuardado = repository.save(nuevoPedido)
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado)
    }

    @PutMapping("/{id}")
    fun actualizarPedido(@PathVariable id: Long, @RequestBody pedidoActualizado: Pedido): ResponseEntity<Pedido> {
        return if (repository.existsById(id)) {
            val pedidoAGuardar = pedidoActualizado.copy(id = id)
            val pedidoGuardado = repository.save(pedidoAGuardar)
            ResponseEntity.ok(pedidoGuardado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarPedido(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}