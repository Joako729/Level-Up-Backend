package com.Level_Up.Mobile.backend_gamer.Pedido

import jakarta.persistence.*

@Entity
@Table(name = "pedido")
data class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val usuarioId: Long,

    val montoTotal: Int,
    val montoDescuento: Int,
    val montoFinal: Int,

    @Column(nullable = false)
    val estado: String,

    val fechaCreacion: Long,

    @Column(columnDefinition = "TEXT")
    val itemsJson: String
)