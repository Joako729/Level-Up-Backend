package com.Level_Up.Mobile.backend_gamer.Resenia

import jakarta.persistence.*

@Entity
@Table(name = "resenia")
data class Resenia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val productoId: Long,

    @Column(nullable = false)
    val usuarioId: Long,

    @Column(nullable = false)
    val nombreUsuario: String,

    @Column(nullable = false)
    val valoracion: Float,

    @Column(nullable = false, columnDefinition = "TEXT")
    val comentario: String,

    @Column(nullable = false)
    val fechaCreacion: Long
)