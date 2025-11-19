package com.edu.oldragon.service

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.ServiceCompat
import com.edu.oldragon.model.BattleSimulator
import kotlinx.coroutines.*

class BattleService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 1. Inicia o serviço em modo Foreground (obrigatório para não morrer)
        val notification = NotificationHelper.buildForegroundNotification(this)

        if (Build.VERSION.SDK_INT >= 34) {
            ServiceCompat.startForeground(
                this,
                1001,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SHORT_SERVICE
            )
        } else {
            startForeground(1001, notification)
        }

        // 2. Pega os dados passados pela tela
        val nome = intent?.getStringExtra("NOME") ?: "Herói"
        val str = intent?.getIntExtra("STR", 10) ?: 10
        val dex = intent?.getIntExtra("DEX", 10) ?: 10
        val con = intent?.getIntExtra("CON", 10) ?: 10

        // 3. Roda a simulação em outra thread
        serviceScope.launch {
            // Simula um tempo de processamento (opcional, só pra dar drama)
            delay(3000)

            val result = BattleSimulator.simulateBattle(nome, str, dex, con)

            // 4. Notifica o resultado
            if (!result.playerWon) {
                NotificationHelper.sendResultNotification(
                    this@BattleService,
                    "Personagem Morto!",
                    "$nome caiu em combate contra o Orc."
                )
            } else {
                NotificationHelper.sendResultNotification(
                    this@BattleService,
                    "Vitória!",
                    "$nome derrotou o Orc e sobreviveu!"
                )
            }

            // 5. Encerra o serviço
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}