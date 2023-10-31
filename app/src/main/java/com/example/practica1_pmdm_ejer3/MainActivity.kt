package com.example.practica1_pmdm_ejer3

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import java.util.Random


class MainActivity : AppCompatActivity() {

    lateinit var CalcularBoton: Button
    lateinit var CambiarColorBoton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CalcularBoton = findViewById(R.id.calcularPrimos)
        CambiarColorBoton = findViewById(R.id.cambiarColor)
    }

    fun onClickCalcular(view: View) {
        val maxNumber = Integer.MAX_VALUE / 40000
        val numerosPrimos = cal_primos(maxNumber)

        Log.d("NumerosPrimos", numerosPrimos.joinToString(", ")) // Imprimir los numeros primos en LogCat

    }

    fun onClickCambiarColor(view: View) {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        CambiarColorBoton.setBackgroundColor(color)
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
}