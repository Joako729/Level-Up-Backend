package com.Level_Up.Mobile.backend_gamer.Producto

import jakarta.persistence.*

// Indica a Hibernate que esta clase es una tabla de la base de datos
@Entity
@Table(name = "productos") // Asigna un nombre a la tabla
data class Producto(

    // Marca el campo como clave primaria y auto-incrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // CAMPOS EXISTENTES
    val nombre: String,
    val precio: Double,
    val descripcion: String?, // El signo de interrogación indica que el campo puede ser nulo

    // NUEVOS CAMPOS A AGREGAR
    val codigo: String,
    val categoria: String,
    val stock: Int,
    val valoracion: Float,
    val urlImagen: String,
    val fabricante: String,
    val destacado: Boolean
)