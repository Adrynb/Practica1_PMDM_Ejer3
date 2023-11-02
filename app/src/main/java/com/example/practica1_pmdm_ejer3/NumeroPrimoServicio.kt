package com.example.practica1_pmdm_ejer3

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class NumeroPrimoServicio : Service() {
    private val CHANNEL_ID = "ForegroundServiceChannel"
    private lateinit var manager: NotificationManager
    private var enable: Boolean = false

    override fun onCreate() {
        super.onCreate()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Ejemplo Servicio primer plano")
            .setContentText("Calculating Prime Numbers...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(102, notification)
        runInBackground()
        return START_NOT_STICKY
    }

    private fun runInBackground() {
        enable = true
        Thread {
            while (enable) {
                Log.e("Service", "Service is running...")
                try {
                    val maxNumber = Integer.MAX_VALUE / 40000
                    val numerosPrimos = cal_primos(maxNumber)
                    val resultadoPrimosParaImprimir = "Numero primos: $numerosPrimos"
                    Log.d("numerosPrimos", resultadoPrimosParaImprimir)

                    val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("Resultado Numeros Primos")
                        .setContentText(resultadoPrimosParaImprimir)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(PendingIntent.getActivity(
                            this,
                            0,
                            Intent(this, MainActivity::class.java),
                            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                        ))
                        .build()

                    manager.notify(103, notification)

                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        enable = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
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

