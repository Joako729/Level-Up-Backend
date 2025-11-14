package com.Level_Up.Mobile.backend_gamer.AppResenia

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/app-resenas") // Endpoint específico para reseñas de la app
class AppReseniaController(private val repository: AppReseniaRepository) {

    // 1. ENDPOINT DE CREACIÓN: POST /api/app-resenas
    @PostMapping
    fun crearReseniaApp(@RequestBody nuevaResenia: AppResenia): ResponseEntity<AppResenia> {
        val reseniaGuardada = repository.save(nuevaResenia)
        return ResponseEntity.status(HttpStatus.CREATED).body(reseniaGuardada)
    }

    // 2. ENDPOINT DE VERIFICACIÓN: GET /api/app-resenas
    @GetMapping
    fun obtenerTodasLasReseniasApp(): List<AppResenia> {
        return repository.findAll()
    }
}