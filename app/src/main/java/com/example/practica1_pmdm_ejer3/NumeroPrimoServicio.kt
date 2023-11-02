@file:Suppress("DEPRECATION")

package com.example.practica1_pmdm_ejer3

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.concurrent.Executors

class NumeroPrimoServicio : Service() {
    private val CHANNEL_ID = "PrimoCanaldeServicio"
    private lateinit var manager: NotificationManager
    private val notificationId = 101
    private val executor = Executors.newSingleThreadExecutor()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("NumeroPrimoServicio")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)

        val notification: Notification = notificationBuilder.build()
        startForeground(notificationId, notification)

        executor.execute {
            val maxNumber = Integer.MAX_VALUE / 40000
            val numerosPrimos = cal_primos(maxNumber)

            val resultadoPrimosParaImprimir = "Numero primos: $numerosPrimos"
            Log.d("numerosPrimos", resultadoPrimosParaImprimir)

            notificationBuilder.setContentText(resultadoPrimosParaImprimir)
            manager.notify(notificationId, notificationBuilder.build())

            stopForeground(true)
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Numeros Primos Canal de Servicio",
            NotificationManager.IMPORTANCE_HIGH
        )
        manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }


    fun cal_primos(n: Int): ArrayList<Int> {
        var elementos = ArrayList<Int>()

        for (i in 2..n) {
            if (esPrimo(i)) {
                elementos.add(i)
            }
        }

        return elementos
    }

    private fun esPrimo(n: Int): Boolean {
        if (n <= 1) {
            return false
        }
        for (i in 2 until n) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }
}
