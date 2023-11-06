package com.example.practica1_pmdm_ejer3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Random


class MainActivity : AppCompatActivity() {

    lateinit var CalcularBoton: Button
    lateinit var CambiarColorBoton: Button
    lateinit var calcularSPboton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CalcularBoton = findViewById(R.id.calcularPrimos)
        CambiarColorBoton = findViewById(R.id.cambiarColor)
    }

    fun onClickCalcular(view: View) {
        val serviceIntent = Intent(this, NumeroPrimoServicio::class.java)
        startService(serviceIntent)
    }

    fun onClickCalcularSP(view: View) {
        val serviceIntent = Intent(this, NumeroPrimosServicioSP::class.java)
        startService(serviceIntent)
    }

    fun onClickCambiarColor(view: View) {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        CambiarColorBoton.setBackgroundColor(color)
    }
    /*
        fun onClickCalcular(view: View) {
            val maxNumber = Integer.MAX_VALUE / 40000
            val numerosPrimos = cal_primos(maxNumber)

            Log.d("NumerosPrimos", numerosPrimos.toString()) // Imprimir los numeros primos en LogCat

        }
        fun cal_primos(n: Int): ArrayList<Int> {
            var elementos = ArrayList<Int>()

            for (i in 1..n) {
                if (esPrimo(i, i - 1)) {
                    elementos.add(i)
                }
            }

            return elementos
        }
        private fun esPrimo(n: Int, divisor: Int): Boolean {
            if (divisor <= 1) {
                return true
            } else if (n % divisor == 0) {
                return false
            } else {
                return esPrimo(n, divisor - 1)
            }
        }
     */
}