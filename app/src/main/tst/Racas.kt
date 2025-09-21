sealed class Raca(
    val nome: String,
    val movimento: Int, // em metros
    val infravisao: Int, // em metros
    val alinhamento: String // Tendência
) {
    abstract fun obterHabilidadesRaciais(): List<String>
}

object Humano : Raca("Humano", 9, 0, "Qualquer") {
    override fun obterHabilidadesRaciais(): List<String> {
        return listOf(
            "APRENDIZADO: Recebe 10% de bônus sobre toda experiência (XP) recebida.",
            "ADAPTABILIDADE: Recebe um bônus de +1 em uma única JP à sua escolha."
        )
    }
}

object Elfo : Raca("Elfo", 9, 18, "Tende à Neutralidade") {
    override fun obterHabilidadesRaciais(): List<String> {
        return listOf(
            "PERCEPÇÃO NATURAL: Chance de detectar portas secretas.",
            "GRACIOSOS: Bônus de +1 em qualquer teste de JPD.",
            "ARMA RACIAL: Bônus de +1 nos danos com arcos.",
            "IMUNIDADES: Imune a sono e paralisia de Ghoul."
        )
    }
}

object Anao : Raca("Anão", 6, 18, "Tende à Ordem") {
    override fun obterHabilidadesRaciais(): List<String> {
        return listOf(
            "MINERADORES: Chance de detectar anomalias em pedras.",
            "VIGOROSO: Bônus de +1 em qualquer teste de JPC.",
            "INIMIGOS: Ataques fáceis contra orcs, ogros e hobgoblins.",
            "RESTRIÇÃO: Não pode usar armas grandes."
        )
    }
}