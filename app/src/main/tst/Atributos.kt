data class Atributos(
    val forca: Int,
    val destreza: Int,
    val constituicao: Int,
    val inteligencia: Int,
    val sabedoria: Int,
    val carisma: Int
) {
    // Calcula o modificador de um atributo baseado na Tabela 1.1
    fun getModificador(valorAtributo: Int): Int {
        return when (valorAtributo) {
            in 2..3 -> -3
            in 4..5 -> -2
            in 6..8 -> -1
            in 13..14 -> 1
            in 15..16 -> 2
            in 17..18 -> 3
            in 19..20 -> 4
            else -> 0
        }
    }
}