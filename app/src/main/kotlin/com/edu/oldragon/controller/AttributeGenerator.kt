package com.edu.oldragon.controller

import kotlin.random.Random

object AttributeGenerator {

    private fun rollDice(n: Int, sides: Int): List<Int> {
        return List(n) { Random.nextInt(1, sides + 1) }
    }

    // clássico: 3d6
    fun rollClassic(): Int {
        return rollDice(3,6).sum()
    }

    // heróico: 4d6 drop lowest
    fun rollHeroic(): Int {
        val rolls = rollDice(4,6).sorted()
        return rolls.subList(1, rolls.size).sum()
    }

    // aventureiro: point-buy simples (27 pontos) - distribuição de 6..15
    // Strategy: start all 8, spend points to increase until points exhausted with simple heuristic/randomness
    fun rollAdventurer(): IntArray {
        val base = IntArray(6) { 8 }
        var points = 27
        val cost: (Int) -> Int = { v ->
            when (v) {
                in 8..13 -> 1
                14 -> 2
                15 -> 2
                else -> Int.MAX_VALUE
            }
        }
        // simple distribution: greedily increase random stats until points end
        val rand = Random
        while (points > 0) {
            val idx = rand.nextInt(0,6)
            val cur = base[idx]
            if (cur >= 15) continue
            val c = cost(cur + 1)
            if (c <= points) {
                base[idx] = cur + 1
                points -= c
            } else break
        }
        return base
    }

    fun generateAttributes(mode: String): IntArray {
        return when(mode) {
            "Clássico", "classico", "clasico" -> IntArray(6) { rollClassic() }
            "Heróico", "heroico", "heroi" -> IntArray(6) { rollHeroic() }
            "Aventureiro", "aventureiro" -> rollAdventurer()
            else -> IntArray(6) { rollClassic() }
        }
    }
}
