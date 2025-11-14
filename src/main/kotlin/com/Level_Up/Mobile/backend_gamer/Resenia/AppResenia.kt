package com.Level_Up.Mobile.backend_gamer.AppResenia // Nuevo Paquete

import jakarta.persistence.*

// Mapeado a la tabla 'app_resenia'
@Entity
@Table(name = "app_resenia")
data class AppResenia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val usuarioId: Long,

    @Column(nullable = false)
    val nombreUsuario: String,

    @Column(nullable = false)
    val valoracion: Float,

    @Column(nullable = false, columnDefinition = "TEXT")
    val comentario: String,

    @Column(nullable = false)
    val fechaCreacion: Long // Timestamp
)