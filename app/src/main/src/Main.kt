// Arquivo: Main.kt

fun main() {
    println("=== CRIADOR DE PERSONAGEM OLD DRAGON ===")

    // 1. Gerar Atributos
    val atributos = AtributoGenerator.gerarAtributos()

    // 2. Escolher Raça
    val raca = escolherRaca()

    // 3. Escolher Classe
    val classe = escolherClasse()

    // 4. Criar o Personagem
    val personagem = Personagem(
        nome = "Aventureiro Teste",
        raca = raca,
        classe = classe,
        nivel = 1,
        atributos = atributos
    )

    // 5. Exibir a ficha completa
    personagem.exibirFicha()
}

fun escolherRaca(): Raca {
    println("\nEscolha a Raça do seu personagem:")
    println("1 - Humano")
    println("2 - Elfo")
    println("3 - Anão")
    return when (readLine()?.toIntOrNull()) {
        2 -> Elfo
        3 -> Anao
        else -> Humano
    }
}

fun escolherClasse(): ClasseDePersonagem {
    println("\nEscolha a Classe do seu personagem:")
    println("1 - Guerreiro")
    println("2 - Ladrão")
    println("3 - Mago")
    return when (readLine()?.toIntOrNull()) {
        2 -> Ladrao
        3 -> Mago
        else -> Guerreiro
    }
}