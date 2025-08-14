data class Personagem(
    val nome: String,
    val raca: String,
    val classe: String,
    val nivel: Int,
    val pontosDeVida: Int,
    val forca: Int,
    val destreza: Int,
    val constituicao: Int,
    val inteligencia: Int,
    val sabedoria: Int,
    val carisma: Int
) {
    fun exibirFicha() {
        println("\n=== Ficha do Personagem ===")
        println("Nome: $nome | Raça: $raca | Classe: $classe | Nível: $nivel")
        println("PV: $pontosDeVida")
        println("Força: $forca | Destreza: $destreza | Constituição: $constituicao")
        println("Inteligência: $inteligencia | Sabedoria: $sabedoria | Carisma: $carisma")
    }
}