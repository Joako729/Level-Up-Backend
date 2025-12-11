package com.Level_Up.Mobile.backend_gamer.AppResenia

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/app-resenas")
class AppReseniaController(private val repository: AppReseniaRepository) {

    @GetMapping
    fun obtenerTodasLasReseniasApp(): List<AppResenia> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun obtenerReseniaPorId(@PathVariable id: Long): ResponseEntity<AppResenia> {
        val resenia = repository.findById(id)
        return if (resenia.isPresent) {
            ResponseEntity.ok(resenia.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun crearReseniaApp(@RequestBody nuevaResenia: AppResenia): ResponseEntity<AppResenia> {
        val reseniaGuardada = repository.save(nuevaResenia)
        return ResponseEntity.status(HttpStatus.CREATED).body(reseniaGuardada)
    }

    @PutMapping("/{id}")
    fun actualizarResenia(@PathVariable id: Long, @RequestBody reseniaActualizada: AppResenia): ResponseEntity<AppResenia> {
        return if (repository.existsById(id)) {
            val reseniaAGuardar = reseniaActualizada.copy(id = id)
            val reseniaGuardada = repository.save(reseniaAGuardar)
            ResponseEntity.ok(reseniaGuardada)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarResenia(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}