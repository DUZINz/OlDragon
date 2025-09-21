package com.edu.oldragon.view.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edu.oldragon.controller.CharacterViewModel
import com.edu.oldragon.model.Options

@Composable
fun CharacterCreatorScreen(vm: CharacterViewModel = viewModel()) {
    val charState by vm.character.collectAsState()
    var name by remember { mutableStateOf(charState.name) }
    val modeOptions = listOf("Clássico", "Heróico", "Aventureiro")
    var selectedMode by remember { mutableStateOf("Clássico") }
    var selectedRace by remember { mutableStateOf(Options.races.first()) }
    var selectedClass by remember { mutableStateOf(Options.classes.first()) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(value = name, onValueChange = {
            name = it
            vm.setName(it)
        }, label = { Text("Nome do personagem") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))

        Text("Modo de geração")
        modeOptions.forEach { mode ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                RadioButton(selected = (selectedMode == mode), onClick = {
                    selectedMode = mode
                    vm.setMode(mode)
                })
                Text(mode, modifier = Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Race selector
        Text("Raça")
        Options.races.forEach { r ->
            Row {
                RadioButton(selected = (selectedRace == r), onClick = {
                    selectedRace = r
                    vm.setRace(r)
                })
                Text(r, modifier = Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Class selector
        Text("Classe")
        Options.classes.forEach { c ->
            Row {
                RadioButton(selected = (selectedClass == c), onClick = {
                    selectedClass = c
                    vm.setClass(c)
                })
                Text(c, modifier = Modifier.padding(start = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { vm.generateAttributes() }, modifier = Modifier.fillMaxWidth()) {
            Text("Gerar Atributos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display attributes
        Text("Atributos:", style = MaterialTheme.typography.h6)
        AttributeRow("Força", charState.strength)
        AttributeRow("Destreza", charState.dexterity)
        AttributeRow("Constituição", charState.constitution)
        AttributeRow("Inteligência", charState.intelligence)
        AttributeRow("Sabedoria", charState.wisdom)
        AttributeRow("Carisma", charState.charisma)

        Spacer(modifier = Modifier.height(12.dp))

        Text("Habilidades (${charState.clazz}):")
        val abilities = com.edu.oldragon.model.Options.abilitiesByClass[charState.clazz] ?: emptyList()
        abilities.forEach { Text("- $it") }
    }
}

@Composable
fun AttributeRow(name: String, value: Int) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(name)
        Text(value.toString())
    }
}
