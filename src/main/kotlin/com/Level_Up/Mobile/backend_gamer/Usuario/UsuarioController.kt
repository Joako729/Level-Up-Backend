package com.Level_Up.Mobile.backend_gamer.Usuario

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val repository: UsuarioRepository) {

    @PostMapping("/registro")
    fun registrarUsuario(@RequestBody nuevoUsuario: Usuario): ResponseEntity<Usuario> {
        if (repository.findByCorreo(nuevoUsuario.correo) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
        val usuarioGuardado = repository.save(nuevoUsuario)
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado)
    }

    @PostMapping("/login")
    fun iniciarSesion(@RequestBody loginRequest: LoginRequest): ResponseEntity<Usuario> {
        val usuario = repository.findByCorreo(loginRequest.correo)

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        if (usuario.contrasena == loginRequest.contrasena) {
            val usuarioSinPass = usuario.copy(contrasena = "")
            return ResponseEntity.ok(usuarioSinPass)
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @GetMapping
    fun obtenerTodosLosUsuarios(): List<Usuario> {
        return repository.findAll().map { it.copy(contrasena = "") }
    }

    @GetMapping("/{id}")
    fun obtenerUsuarioPorId(@PathVariable id: Long): ResponseEntity<Usuario> {
        val usuario = repository.findById(id)
        return if (usuario.isPresent) {
            val usuarioSeguro = usuario.get().copy(contrasena = "")
            ResponseEntity.ok(usuarioSeguro)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuarioActualizado: Usuario): ResponseEntity<Usuario> {
        return if (repository.existsById(id)) {
            val usuarioAGuardar = usuarioActualizado.copy(id = id)
            val usuarioGuardado = repository.save(usuarioAGuardar)
            ResponseEntity.ok(usuarioGuardado.copy(contrasena = ""))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}