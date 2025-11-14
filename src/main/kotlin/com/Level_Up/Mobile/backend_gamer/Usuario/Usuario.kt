package com.Level_Up.Mobile.backend_gamer.Usuario

import jakarta.persistence.*

// Mapeado a la tabla 'Usuario' en PostgreSQL
@Entity
@Table(name = "usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false, unique = true)
    val correo: String,

    @Column(nullable = false)
    val edad: Int,

    @Column(nullable = false)
    val contrasena: String,

    @Column(nullable = false)
    val esDuoc: Boolean = false,

    @Column(nullable = false)
    val puntosLevelUp: Int = 0,

    @Column(nullable = false)
    val nivel: Int = 1,

    @Column(nullable = false, unique = true)
    val codigoReferido: String = "",

    val referidoPor: String = "",

    @Column(nullable = false)
    val totalCompras: Int = 0,

    @Column(nullable = false)
    val sesionIniciada: Boolean = false
)