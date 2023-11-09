@file:Suppress("DEPRECATION")

package com.example.practica1_pmdm_ejer3

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "com.example.practica1_pmdm_ejer3.action.FOO"
private const val ACTION_BAZ = "com.example.practica1_pmdm_ejer3.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.example.practica1_pmdm_ejer3.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.example.practica1_pmdm_ejer3.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
const val ACTION_CALCULATE_PRIME = "com.example.practica1_pmdm_ejer3.action.CALCULATE_PRIME"
const val EXTRA_NUMBER = "com.example.practica1_pmdm_ejer3.extra.NUMBER"
private const val CHANNEL_ID = "channel_01"

class NumeroPrimosServicioInten : IntentService("NumeroPrimosServicioInten") {

    @SuppressLint("MissingPermission")
    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_CALCULATE_PRIME -> {
                val number = intent.getIntExtra(EXTRA_NUMBER, 0)
                val primeNumbers = calculatePrimeNumbers(number)

                Log.i("NumeroPrimosServicioInten", "Numeros primos de $number: $primeNumbers")

                val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Calculo primos")
                    .setContentText("Numeros primos de $number: $primeNumbers")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                NotificationManagerCompat.from(this).notify(1, notificationBuilder.build())            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_01"
            val descriptionText = "intent serivce notifcation"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun calculatePrimeNumbers(number: Int): List<Int> {
        val primes = mutableListOf<Int>()
        for (i in 2..number) {
            if (isPrime(i)) {
                primes.add(i)
            }
        }
        return primes
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        for (i in 2 until number) {
            if (number % i == 0) return false
        }
        return true
    }

}