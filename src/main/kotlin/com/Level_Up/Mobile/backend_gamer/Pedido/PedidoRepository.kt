package com.Level_Up.Mobile.backend_gamer.Pedido

import org.springframework.data.jpa.repository.JpaRepository

interface PedidoRepository : JpaRepository<Pedido, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario (ej. findByUsuarioId)
}