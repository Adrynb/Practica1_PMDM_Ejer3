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
        val numerosPrimos = (1..maxNumber).filter { esPrimo(it) }

        // Imprimir los números primos en LogCat
        Log.d("NumerosPrimos", numerosPrimos.joinToString(", "))
    }

    fun onClickCambiarColor(view: View) {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        CambiarColorBoton.setBackgroundColor(color)
    }

    private fun esPrimo(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2 until num) {
            if (num % i == 0) return false
        }
        return true
    }
}