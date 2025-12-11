package com.Level_Up.Mobile.backend_gamer.Producto

import jakarta.persistence.*

@Entity
@Table(name = "productos")
data class Producto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nombre: String,
    val precio: Double,
    val descripcion: String?,
    val codigo: String,
    val categoria: String,
    val stock: Int,
    val valoracion: Float,
    val urlImagen: String,
    val fabricante: String,
    val destacado: Boolean
)