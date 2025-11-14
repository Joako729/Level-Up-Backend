package com.Level_Up.Mobile.backend_gamer.Pedido

import jakarta.persistence.*

// Mapeado a la tabla 'pedido' en PostgreSQL
@Entity
@Table(name = "pedido")
data class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val usuarioId: Long,

    // <<-- CAMBIOS TEMPORALES: SE ELIMINÓ @Column(nullable = false) -->>
    val montoTotal: Int,
    val montoDescuento: Int,
    val montoFinal: Int,
    // <<-- FIN CAMBIOS TEMPORALES -->>

    @Column(nullable = false)
    val estado: String, // 'pending', 'completed', 'cancelled'

    // <<-- CAMBIOS TEMPORALES: SE ELIMINÓ @Column(nullable = false) -->>
    val fechaCreacion: Long, // Timestamp

    @Column(columnDefinition = "TEXT") // SE ELIMINÓ nullable = false
    val itemsJson: String // JSON o String serializado de los ítems
)