import kotlin.random.Random

fun main() {
    println("Escolha o estilo de rolagem de atributos:")
    println("1 - Clássico (3d6 na ordem)")
    println("2 - Aventureiro (3d6 e distribuir)")
    println("3 - Heroico (4d6 descartando o menor e distribuir)")
    val escolha = readLine()?.toIntOrNull() ?: 1

    val atributos = when (escolha) {
        2 -> distribuirValores(rolarClassico())
        3 -> distribuirValores(List(6) { rolar4d6MenorDescarta() })
        else -> rolarClassico() // Clássico é automático, na ordem
    }

    val personagem = Personagem(
        nome = "Arthos",
        raca = "Humano",
        classe = "Guerreiro",
        nivel = 1,
        pontosDeVida = 12,
        forca = atributos[0],
        destreza = atributos[1],
        constituicao = atributos[2],
        inteligencia = atributos[3],
        sabedoria = atributos[4],
        carisma = atributos[5]
    )

    personagem.exibirFicha()
}

// Rola dados
fun rolarD6(qtd: Int): List<Int> = List(qtd) { Random.nextInt(1, 7) }
fun rolar3d6(): Int = rolarD6(3).sum()
fun rolar4d6MenorDescarta(): Int {
    val dados = rolarD6(4)
    return dados.sortedDescending().take(3).sum()
}

// Estilos de rolagem
fun rolarClassico(): List<Int> = List(6) { rolar3d6() }

// Função para distribuição manual
fun distribuirValores(valores: List<Int>): List<Int> {
    val atributosNomes = listOf("Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma")
    val distribuido = MutableList(6) { 0 }
    val valoresDisponiveis = valores.toMutableList()

    println("Valores rolados: ${valoresDisponiveis.joinToString(", ")}")
    for (i in atributosNomes.indices) {
        println("Escolha o valor para ${atributosNomes[i]}:")
        println("Valores disponíveis: ${valoresDisponiveis.joinToString(", ")}")
        var escolhido: Int? = null
        while (escolhido == null) {
            val input = readLine()?.toIntOrNull()
            if (input != null && valoresDisponiveis.contains(input)) {
                escolhido = input
                distribuido[i] = escolhido
                valoresDisponiveis.remove(escolhido)
            } else {
                println("Valor inválido. Escolha novamente.")
            }
        }
    }
    return distribuido
}


