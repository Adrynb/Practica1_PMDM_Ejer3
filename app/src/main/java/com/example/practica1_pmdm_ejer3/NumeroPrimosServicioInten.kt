package com.example.practica1_pmdm_ejer3

import android.Manifest
import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
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

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_CALCULATE_PRIME -> {
                val number = intent.getIntExtra(EXTRA_NUMBER, 0)
                handleActionCalculatePrime(number)
            }
        }
    }

    private fun handleActionCalculatePrime(number: Int) {
        val primeNumbers = calculatePrimeNumbers(number)
        val result = primeNumbers.joinToString(separator = ", ")

        // Log result
        Log.i("NumeroPrimosServicioInten", "Prime numbers of $number: $result")

        // Notify result
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Prime Calculation")
            .setContentText("Prime numbers of $number: $result")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this).notify(1, notificationBuilder.build())
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

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String?, param2: String?) {
        TODO("Handle action Foo")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String?, param2: String?) {
        TODO("Handle action Baz")
    }

    companion object {
        @JvmStatic
        fun startActionCalculatePrime(context: Context, number: Int) {
            val intent = Intent(context, NumeroPrimosServicioInten::class.java).apply {
                action = ACTION_CALCULATE_PRIME
                putExtra(EXTRA_NUMBER, number)
            }
            context.startService(intent)
        }
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, NumeroPrimosServicioInten::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, NumeroPrimosServicioInten::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}