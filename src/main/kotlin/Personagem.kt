// Arquivo: Personagem.kt

class Personagem(
    val nome: String,
    val raca: Raca,
    val classe: ClasseDePersonagem,
    val nivel: Int,
    val atributos: Atributos
) {
    val pontosDeVida: Int = calcularPvIniciais()

    private fun calcularPvIniciais(): Int {
        // Regra da pág. 50: Valor fixo da classe + mod. de Constituição
        val modCon = atributos.getModificador(valorAtributo = atributos.constituicao)
        return classe.pontosDeVidaIniciais + modCon
    }

    fun exibirFicha() {
        println("\n===============================")
        println("        FICHA DO PERSONAGEM      ")
        println("===============================")
        println("Nome: $nome | Nível: $nivel")
        println("Raça: ${raca.nome} | Classe: ${classe.nome}")
        println("Alinhamento: ${raca.alinhamento}")
        println("--- Atributos ---")
        println("FOR: ${atributos.forca} (Mod: ${atributos.getModificador(atributos.forca)})")
        println("DES: ${atributos.destreza} (Mod: ${atributos.getModificador(atributos.destreza)})")
        println("CON: ${atributos.constituicao} (Mod: ${atributos.getModificador(atributos.constituicao)})")
        println("INT: ${atributos.inteligencia} (Mod: ${atributos.getModificador(atributos.inteligencia)})")
        println("SAB: ${atributos.sabedoria} (Mod: ${atributos.getModificador(atributos.sabedoria)})")
        println("CAR: ${atributos.carisma} (Mod: ${atributos.getModificador(atributos.carisma)})")
        println("--- Combate ---")
        println("Pontos de Vida (PV): $pontosDeVida")
        println("Movimento: ${raca.movimento}m | Infravi̇são: ${raca.infravisao}m")
        println("--- Habilidades Raciais (${raca.nome}) ---")
        raca.obterHabilidadesRaciais().forEach { println("- $it") }
        println("--- Habilidades de Classe (${classe.nome} Nv. $nivel) ---")
        classe.obterHabilidadesDeClasse(nivel).forEach { println("- $it") }
        println("===============================")
    }
}