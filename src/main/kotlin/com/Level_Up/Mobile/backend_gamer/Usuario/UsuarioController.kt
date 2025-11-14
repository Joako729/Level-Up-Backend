package com.Level_Up.Mobile.backend_gamer.Usuario

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios") // Ruta base: http://localhost:8080/api/usuarios
class UsuarioController(private val repository: UsuarioRepository) {

    // 1. ENDPOINT DE REGISTRO: POST /api/usuarios/registro
    @PostMapping("/registro")
    fun registrarUsuario(@RequestBody nuevoUsuario: Usuario): ResponseEntity<Usuario> {
        // En un caso real, la contraseña debe ser hasheada aquí.

        // 1. Validación: Verificar si el correo ya está registrado
        if (repository.findByCorreo(nuevoUsuario.correo) != null) {
            // 409 Conflict: El recurso que intentas crear ya existe
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }

        // 2. Guardar el nuevo usuario en la base de datos
        val usuarioGuardado = repository.save(nuevoUsuario)
        // 201 Created: Respuesta exitosa de creación de recurso
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado)
    }

    // 2. ENDPOINT DE INICIO DE SESIÓN: POST /api/usuarios/login
    @PostMapping("/login")
    fun iniciarSesion(@RequestBody loginRequest: LoginRequest): ResponseEntity<Usuario> {
        val usuario = repository.findByCorreo(loginRequest.correo)

        // 1. Verificar si el usuario existe
        if (usuario == null) {
            // 401 Unauthorized: Credenciales inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        // 2. Verificar la contraseña (comparación simple en este ejemplo)
        if (usuario.contrasena == loginRequest.contrasena) {
            // 3. Devolver el usuario (filtrando la contraseña por seguridad)
            val usuarioSinPass = usuario.copy(contrasena = "")
            return ResponseEntity.ok(usuarioSinPass) // 200 OK
        } else {
            // Contraseña incorrecta
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() // 401 Unauthorized
        }
    }

    // 3. ENDPOINT DE PRUEBA (Opcional): GET /api/usuarios
    @GetMapping
    fun obtenerTodosLosUsuarios(): List<Usuario> {
        // Mapeamos para no exponer contraseñas en las respuestas GET
        return repository.findAll().map { it.copy(contrasena = "") }
    }
    // ... dentro de la clase UsuarioController(private val repository: UsuarioRepository) {

    // 3. ENDPOINT DE ELIMINACIÓN: DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}") // {id} es una variable de ruta
    fun eliminarUsuario(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            // 204 No Content: Indica que la petición fue exitosa, pero no hay contenido para devolver.
            ResponseEntity.noContent().build()
        } else {
            // 404 Not Found: El usuario con ese ID no existe.
            ResponseEntity.notFound().build()
        }
    }

    // ... (Mantener los otros métodos: registrarUsuario, iniciarSesion, obtenerTodosLosUsuarios)
}