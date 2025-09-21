// Arquivo: AtributoGenerator.kt
import kotlin.random.Random

object AtributoGenerator {

    // --- Funções Privadas para Rolar Dados ---

    private fun rolarD6(qtd: Int): List<Int> = List(qtd) { Random.nextInt(1, 7) }

    private fun rolar3d6(): Int = rolarD6(3).sum()

    private fun rolar4d6MenorDescarta(): Int {
        // Rola 4 dados, ordena do maior para o menor, pega os 3 primeiros e soma
        return rolarD6(4).sortedDescending().take(3).sum()
    }

    // --- Função Principal para Gerar Atributos ---

    fun gerarAtributos(): Atributos {
        println("\nEscolha o estilo de rolagem de atributos:")
        println("1 - Clássico (3d6 na ordem)")
        println("2 - Aventureiro (3d6 e distribuir)")
        println("3 - Heroico (4d6 descartando o menor e distribuir)")
        val escolha = readLine()?.toIntOrNull() ?: 1

        val valores: List<Int>
        when (escolha) {
            // Estilo Aventureiro: 3d6 para distribuir
            2 -> {
                println("\n--- Estilo Aventureiro ---")
                val valoresRolados = List(6) { rolar3d6() }
                valores = distribuirValores(valoresRolados)
            }
            // Estilo Heroico: 4d6 descartando o menor, para distribuir
            3 -> {
                println("\n--- Estilo Heroico ---")
                val valoresRolados = List(6) { rolar4d6MenorDescarta() }
                valores = distribuirValores(valoresRolados)
            }
            // Estilo Clássico: 3d6 na ordem
            else -> {
                println("\n--- Estilo Clássico ---")
                valores = List(6) { rolar3d6() }
                println("Valores rolados na ordem (For, Des, Con, Int, Sab, Car): ${valores.joinToString(", ")}")
            }
        }

        return Atributos(
            forca = valores[0],
            destreza = valores[1],
            constituicao = valores[2],
            inteligencia = valores[3],
            sabedoria = valores[4],
            carisma = valores[5]
        )
    }

    // --- Função Privada para Distribuir os Valores ---

    private fun distribuirValores(valores: List<Int>): List<Int> {
        val atributosNomes = listOf("Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma")
        val distribuido = MutableList(6) { 0 }
        val valoresDisponiveis = valores.toMutableList()

        println("Valores rolados para distribuir: ${valoresDisponiveis.joinToString(", ")}")

        for (i in atributosNomes.indices) {
            println("\nEscolha o valor para ${atributosNomes[i]}:")
            println("Valores disponíveis: ${valoresDisponiveis.joinToString(", ")}")

            while (true) {
                val input = readLine()?.toIntOrNull()
                if (input != null && valoresDisponiveis.contains(input)) {
                    distribuido[i] = input
                    valoresDisponiveis.remove(input)
                    break // Sai do loop while e vai para o próximo atributo
                } else {
                    println("Valor inválido ou indisponível. Por favor, escolha um da lista.")
                }
            }
        }
        println("\nAtributos distribuídos com sucesso!")
        return distribuido
    }
}