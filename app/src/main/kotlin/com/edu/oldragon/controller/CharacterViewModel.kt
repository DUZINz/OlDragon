package com.edu.oldragon.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.edu.oldragon.model.database.AppDatabase
import com.edu.oldragon.model.database.PersonagemEntity
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "ol_dragon_db"
    ).allowMainThreadQueries() // <-- ADICIONE ESTA LINHA
        .build()

    private val dao = db.personagemDao()

    fun salvarPersonagem(
        nome: String,
        raca: String,
        classe: String,
        nivel: Int,
        forca: Int,
        destreza: Int,
        constituicao: Int,
        inteligencia: Int,
        sabedoria: Int,
        carisma: Int
    ) {
        viewModelScope.launch {
            val personagem = PersonagemEntity(
                nome = nome,
                raca = raca,
                classe = classe,
                nivel = nivel,
                forca = forca,
                destreza = destreza,
                constituicao = constituicao,
                inteligencia = inteligencia,
                sabedoria = sabedoria,
                carisma = carisma
            )
            dao.inserir(personagem)
        }
    }
}