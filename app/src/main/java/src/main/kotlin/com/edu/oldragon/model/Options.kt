package com.edu.oldragon.model

object Options {
    val races = listOf("Humano", "Elfo", "Anão")        // altere para as suas
    val classes = listOf("Guerreiro", "Mago", "Ladino") // altere para as suas

    // habilidades (podem ser só listadas)
    val abilitiesByClass = mapOf(
        "Guerreiro" to listOf("Ataque Físico", "Defesa"),
        "Mago" to listOf("Bola de Fogo", "Teleport"),
        "Ladino" to listOf("Furtividade", "Crítico")
    )
}
