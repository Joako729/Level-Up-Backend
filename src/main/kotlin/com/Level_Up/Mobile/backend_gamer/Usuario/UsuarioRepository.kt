package com.Level_Up.Mobile.backend_gamer.Usuario

import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    // Spring Data JPA crea esta consulta automáticamente: SELECT * FROM usuario WHERE correo = ?
    fun findByCorreo(correo: String): Usuario?
}