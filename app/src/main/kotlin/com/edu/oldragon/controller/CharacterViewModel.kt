package com.edu.oldragon.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.edu.oldragon.model.database.AppDatabase
import com.edu.oldragon.model.database.PersonagemEntity
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val personagemDao = AppDatabase.getInstance(application).personagemDao()

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
            personagemDao.inserir(personagem)
        }
    }

    fun listarPersonagens(onResult: (List<PersonagemEntity>) -> Unit) {
        viewModelScope.launch {
            val personagens = personagemDao.listarTodos()
            onResult(personagens)
        }
    }
}
