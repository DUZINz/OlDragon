package com.edu.oldragon.model

import kotlin.random.Random

data class BattleResult(val playerWon: Boolean, val log: String)

object BattleSimulator {

    // Função auxiliar para calcular modificador (Regra: (Atributo - 10) / 2)
    private fun getMod(attr: Int): Int = (attr - 10) / 2

    fun simulateBattle(
        heroName: String,
        str: Int,
        dex: Int,
        con: Int
    ): BattleResult {
        // Status do Herói
        var heroHp = 10 + getMod(con) // Base 10 + mod CON
        val heroAc = 10 + getMod(dex) // Base 10 + mod DEX
        val heroAtkBonus = getMod(str)

        // Inimigo: Orc Comum
        val enemyName = "Orc"
        var enemyHp = 12
        val enemyAc = 12
        val enemyAtkBonus = 2

        val log = StringBuilder()
        log.append("Batalha iniciada: $heroName (HP:$heroHp) vs $enemyName (HP:$enemyHp)\n")

        var round = 1
        while (heroHp > 0 && enemyHp > 0) {
            // --- Turno do Herói ---
            val heroRoll = Random.nextInt(1, 21)
            val heroTotalAtk = heroRoll + heroAtkBonus

            if (heroTotalAtk >= enemyAc) {
                val dmg = Random.nextInt(1, 9) + getMod(str) // Dano 1d8 + Força
                val finalDmg = dmg.coerceAtLeast(1)
                enemyHp -= finalDmg
                log.append("Round $round: Você acertou! Dano: $finalDmg. Orc HP: $enemyHp\n")
            } else {
                log.append("Round $round: Você errou o ataque.\n")
            }

            if (enemyHp <= 0) break

            // --- Turno do Inimigo ---
            val enemyRoll = Random.nextInt(1, 21)
            val enemyTotalAtk = enemyRoll + enemyAtkBonus

            if (enemyTotalAtk >= heroAc) {
                val dmg = Random.nextInt(1, 7) // Dano 1d6
                heroHp -= dmg
                log.append("Round $round: Orc te acertou! Dano: $dmg. Seu HP: $heroHp\n")
            } else {
                log.append("Round $round: Orc errou.\n")
            }

            round++
        }

        val playerWon = heroHp > 0
        log.append(if (playerWon) "VITÓRIA! O inimigo caiu." else "DERROTA! Seu personagem morreu.")

        return BattleResult(playerWon, log.toString())
    }
}