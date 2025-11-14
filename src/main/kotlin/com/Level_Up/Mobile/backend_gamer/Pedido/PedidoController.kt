package com.Level_Up.Mobile.backend_gamer.Pedido

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pedidos") // Ruta base: http://localhost:8080/api/pedidos
class PedidoController(private val repository: PedidoRepository) {

    // 1. ENDPOINT DE CREACIÓN DE PEDIDO: POST /api/pedidos
    @PostMapping
    fun crearNuevoPedido(@RequestBody nuevoPedido: Pedido): ResponseEntity<Pedido> {
        // En este punto, Spring Boot y Hibernate validarán automáticamente que los campos
        // marcados como 'nullable = false' lleguen en el cuerpo del JSON.

        val pedidoGuardado = repository.save(nuevoPedido)

        // 201 Created: El pedido fue guardado exitosamente.
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado)
    }

    // 2. ENDPOINT ADICIONAL: GET /api/pedidos (Para propósitos de prueba/admin)
    @GetMapping
    fun obtenerTodosLosPedidos(): List<Pedido> {
        return repository.findAll()
    }
}