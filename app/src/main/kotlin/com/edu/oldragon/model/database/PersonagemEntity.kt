package com.edu.oldragon.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagens")
data class PersonagemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "raca") val raca: String,
    @ColumnInfo(name = "classe") val classe: String,
    @ColumnInfo(name = "nivel") val nivel: Int,
    @ColumnInfo(name = "forca") val forca: Int,
    @ColumnInfo(name = "destreza") val destreza: Int,
    @ColumnInfo(name = "constituicao") val constituicao: Int,
    @ColumnInfo(name = "inteligencia") val inteligencia: Int,
    @ColumnInfo(name = "sabedoria") val sabedoria: Int,
    @ColumnInfo(name = "carisma") val carisma: Int
)
