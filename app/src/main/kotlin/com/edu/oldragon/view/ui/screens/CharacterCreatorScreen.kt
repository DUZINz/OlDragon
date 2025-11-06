package com.edu.oldragon.view.ui.screens
import android.widget.Toast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.edu.oldragon.controller.CharacterViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edu.oldragon.model.Options
import com.edu.oldragon.controller.AttributeGenerator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreatorScreen() {
    val context = LocalContext.current
    val viewModel: CharacterViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as android.app.Application)
    )

    var nomePersonagem by remember { mutableStateOf("") }
    var selectedRace by remember { mutableStateOf("Humano") }
    var selectedClass by remember { mutableStateOf("Guerreiro") }

    val races = Options.races
    val classes = Options.classes

    Scaffold(
        topBar = { TopAppBar(title = { Text("Criação de Personagem") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                OutlinedTextField(
                    value = nomePersonagem,
                    onValueChange = { nomePersonagem = it },
                    label = { Text("Nome do Personagem") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Text("Raça", fontWeight = FontWeight.Bold)
                DropdownSelector(races, selectedRace) { selectedRace = it }
            }

            item {
                Text("Classe", fontWeight = FontWeight.Bold)
                DropdownSelector(classes, selectedClass) { selectedClass = it }
            }

            item {
                Button(
                    onClick = {
                        val atributos = AttributeGenerator.generateAttributes("Heróico")
                        viewModel.salvarPersonagem(
                            nome = nomePersonagem,
                            raca = selectedRace,
                            classe = selectedClass,
                            nivel = 1,
                            forca = atributos[0],
                            destreza = atributos[1],
                            constituicao = atributos[2],
                            inteligencia = atributos[3],
                            sabedoria = atributos[4],
                            carisma = atributos[5]
                        )
                        nomePersonagem = ""
                        selectedRace = "Humano"
                        selectedClass = "Guerreiro"
                        Toast.makeText(context, "Personagem salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Criar e Salvar Personagem")
                }

            }
        }
    }
}
@Composable
fun DropdownSelector(options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selected)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
