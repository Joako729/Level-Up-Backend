package com.Level_Up.Mobile.backend_gamer.Producto

import org.springframework.data.jpa.repository.JpaRepository

// JpaRepository hereda automáticamente métodos como findAll(), save(), delete()
interface ProductoRepository : JpaRepository<Producto, Long> {
    // No tienes que escribir ningún código aquí. Spring Boot hace el trabajo.
}