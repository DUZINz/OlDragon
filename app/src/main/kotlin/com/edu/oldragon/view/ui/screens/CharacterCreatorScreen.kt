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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edu.oldragon.controller.AttributeGenerator
import com.edu.oldragon.controller.CharacterViewModel
import com.edu.oldragon.model.Options

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreatorScreen() {
    val context = LocalContext.current
    val viewModel: CharacterViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as android.app.Application)
    )

    // Estados do Formulário
    var nomePersonagem by remember { mutableStateOf("") }
    var selectedRace by remember { mutableStateOf("Humano") }
    var selectedClass by remember { mutableStateOf("Guerreiro") }

    // Novo Estado: Método de Geração
    val methods = listOf("Clássico", "Heróico", "Aventureiro")
    var selectedMethod by remember { mutableStateOf("Clássico") }

    // Estado para guardar os atributos gerados antes de salvar
    var generatedAttributes by remember { mutableStateOf<IntArray?>(null) }

    val races = Options.races
    val classes = Options.classes

    Scaffold(
        topBar = { TopAppBar(title = { Text("Novo Personagem Old Dragon") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Nome
            item {
                OutlinedTextField(
                    value = nomePersonagem,
                    onValueChange = { nomePersonagem = it },
                    label = { Text("Nome do Personagem") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 2. Seletores
            item {
                Text("Raça", fontWeight = FontWeight.Bold)
                DropdownSelector(races, selectedRace) { selectedRace = it }
            }

            item {
                Text("Classe", fontWeight = FontWeight.Bold)
                DropdownSelector(classes, selectedClass) { selectedClass = it }
            }

            item {
                Text("Método de Atributos", fontWeight = FontWeight.Bold)
                DropdownSelector(methods, selectedMethod) {
                    selectedMethod = it
                    generatedAttributes = null // Reseta se mudar o método
                }
            }

            // 3. Área de Atributos
            item {
                Button(
                    onClick = {
                        generatedAttributes = AttributeGenerator.generateAttributes(selectedMethod)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (generatedAttributes == null) "Rolar Atributos" else "Rolar Novamente")
                }

                // Mostra os atributos se já foram gerados
                generatedAttributes?.let { attrs ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Força: ${attrs[0]}  |  Inteligência: ${attrs[3]}")
                            Text("Destreza: ${attrs[1]}  |  Sabedoria: ${attrs[4]}")
                            Text("Constituição: ${attrs[2]}  |  Carisma: ${attrs[5]}")
                        }
                    }
                }
            }

            // 4. Botão Salvar Final
            item {
                Button(
                    enabled = nomePersonagem.isNotEmpty() && generatedAttributes != null,
                    onClick = {
                        val atributos = generatedAttributes!! // Já sabemos que não é null
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

                        Toast.makeText(context, "Personagem salvo!", Toast.LENGTH_SHORT).show()

                        // Limpar campos
                        nomePersonagem = ""
                        generatedAttributes = null
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar no Banco de Dados")
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