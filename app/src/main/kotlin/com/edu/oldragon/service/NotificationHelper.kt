package com.edu.oldragon.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlin.random.Random // <--- O IMPORT CORRETO ESTÁ AQUI AGORA

object NotificationHelper {
    private const val CHANNEL_ID = "battle_channel"
    private const val CHANNEL_NAME = "Simulação de Batalha"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    // Notificação fixa (enquanto roda)
    fun buildForegroundNotification(context: Context): Notification {
        createNotificationChannel(context)
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Old Dragon")
            .setContentText("Batalha acontecendo...")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setOngoing(true)
            .build()
    }

    // Notificação de resultado final
    fun sendResultNotification(context: Context, title: String, message: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.stat_notify_error) // Ícone de alerta
            .setAutoCancel(true)
            .build()

        manager.notify(Random.nextInt(), notification) // ID aleatório para não sobrescrever
    }
}