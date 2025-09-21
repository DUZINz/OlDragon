package com.edu.oldragon.view.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreatorScreen() {
    // Estados da tela
    var selectedMode by remember { mutableStateOf("Clássico") }
    var selectedRace by remember { mutableStateOf("Humano") }
    var selectedClass by remember { mutableStateOf("Guerreiro") }

    val modes = listOf("Clássico", "Heróico", "Aventureiro")
    val races = listOf("Humano", "Elfo", "Anão")
    val classes = listOf("Guerreiro", "Mago", "Ladino")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Criação de Personagem") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Modo de atributos
            item {
                Text("Modo de atributos", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                modes.forEach { mode ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (selectedMode == mode),
                                onClick = { selectedMode = mode }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (selectedMode == mode),
                            onClick = { selectedMode = mode }
                        )
                        Text(mode, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            // Raça
            item {
                Text("Raça", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                DropdownMenuBox(
                    items = races,
                    selectedItem = selectedRace,
                    onItemSelected = { selectedRace = it }
                )
            }

            // Classe
            item {
                Text("Classe", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                DropdownMenuBox(
                    items = classes,
                    selectedItem = selectedClass,
                    onItemSelected = { selectedClass = it }
                )
            }

            // Atributos (placeholder)
            item {
                Text("Atributos", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Column(Modifier.padding(8.dp)) {
                    listOf("Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma").forEach {
                        Text("$it: ${atributoFake(selectedMode)}")
                    }
                }
            }

            // Botão de confirmar
            item {
                Button(
                    onClick = {
                        // Aqui futuramente podemos salvar o personagem
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Criar Personagem")
                }
            }
        }
    }
}

@Composable
fun DropdownMenuBox(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(selectedItem)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

// Função fake para gerar atributos por modo
fun atributoFake(mode: String): Int {
    return when (mode) {
        "Clássico" -> (3..18).random()
        "Heróico" -> (10..18).random()
        "Aventureiro" -> (8..18).random()
        else -> (3..18).random()
    }
}
