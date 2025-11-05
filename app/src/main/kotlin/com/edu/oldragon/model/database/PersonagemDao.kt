package com.edu.oldragon.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonagemDao {
    @Insert
    suspend fun inserir(personagem: PersonagemEntity)

    @Query("SELECT * FROM personagens")
    suspend fun listarTodos(): List<PersonagemEntity>
}
