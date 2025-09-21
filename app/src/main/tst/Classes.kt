sealed class ClasseDePersonagem(
    val nome: String,
    val pontosDeVidaIniciais: Int, // PV fixos no 1º nível
    val dadoDeVida: String // Dado para evoluir PV
) {
    abstract fun obterHabilidadesDeClasse(nivel: Int): List<String>
}

object Guerreiro : ClasseDePersonagem("Guerreiro", 10, "1d10") {
    override fun obterHabilidadesDeClasse(nivel: Int): List<String> {
        val habilidades = mutableListOf(
            "ARMAS: Pode usar todas as armas.",
            "ARMADURAS: Pode usar todas as armaduras.",
            "APARAR: Pode sacrificar escudo ou arma para absorver todo o dano de um ataque.",
            "MAESTRIA EM ARMA: Bônus de +1 no dano com uma arma à sua escolha."
        )
        if (nivel >= 6) {
            habilidades.add("ATAQUE EXTRA: Adquire um segundo ataque por rodada.")
        }
        return habilidades
    }
}

object Ladrao : ClasseDePersonagem("Ladrão", 6, "1d6") {
    override fun obterHabilidadesDeClasse(nivel: Int): List<String> {
        return listOf(
            "ARMAS: Apenas pequenas ou médias.",
            "ARMADURAS: Apenas as leves.",
            "ATAQUE FURTIVO: Causa dano multiplicado por 2 ao atacar furtivamente.",
            "OUVIR RUÍDOS: Chance de 1-2 em 1d6 para ouvir ruídos.",
            "TALENTOS DE LADRÃO: Habilidades como Arrombar, Furtividade, etc."
        )
    }
}

object Mago : ClasseDePersonagem("Mago", 4, "1d4") {
    override fun obterHabilidadesDeClasse(nivel: Int): List<String> {
        return listOf(
            "ARMAS: Apenas pequenas.",
            "ARMADURAS: Nenhuma.",
            "MAGIAS ARCANAS: Capaz de lançar magias arcanas.",
            "GRIMÓRIO: Começa com 4 magias de 1º círculo."
        )
    }
}