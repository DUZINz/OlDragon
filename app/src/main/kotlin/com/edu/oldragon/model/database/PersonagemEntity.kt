package com.edu.oldragon.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagens")
data class PersonagemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val raca: String,
    val classe: String,
    val nivel: Int,
    val forca: Int,
    val destreza: Int,
    val constituicao: Int,
    val inteligencia: Int,
    val sabedoria: Int,
    val carisma: Int
)
