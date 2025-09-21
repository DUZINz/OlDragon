package com.edu.oldragon.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.oldragon.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val _character = MutableStateFlow(Character())
    val character: StateFlow<Character> = _character

    private val _mode = MutableStateFlow("Clássico")
    val mode: StateFlow<String> = _mode

    fun setMode(m: String) { _mode.value = m }

    fun setRace(r: String) {
        viewModelScope.launch {
            val c = _character.value.copy(race = r)
            _character.value = c
        }
    }
    fun setClass(clazz: String) {
        viewModelScope.launch {
            val c = _character.value.copy(clazz = clazz)
            _character.value = c
        }
    }

    fun setName(name: String) {
        viewModelScope.launch {
            _character.value = _character.value.copy(name = name)
        }
    }

    fun generateAttributes() {
        viewModelScope.launch {
            val arr = AttributeGenerator.generateAttributes(_mode.value)
            _character.value = _character.value.copy(
                strength = arr[0],
                dexterity = arr[1],
                constitution = arr[2],
                intelligence = arr[3],
                wisdom = arr[4],
                charisma = arr[5]
            )
        }
    }
}
