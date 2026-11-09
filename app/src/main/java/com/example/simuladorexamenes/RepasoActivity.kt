package com.example.simuladorexamenes

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RepasoActivity : AppCompatActivity() {

    private var indiceActual = 0
    private var estaVolteada = false
    private lateinit var listaRepaso: List<Pregunta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_repaso)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            finish()
        }

        listaRepaso = RepositorioPreguntas.listaPreguntas

        if (listaRepaso.isEmpty()) {
            Toast.makeText(this, "No hay tarjetas para repasar", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val tarjetaFlashcard = findViewById<LinearLayout>(R.id.tarjetaFlashcard)
        val btnSiguiente = findViewById<Button>(R.id.btnSiguienteTarjeta)

        mostrarTarjeta()


        tarjetaFlashcard.setOnClickListener {
            val tvTexto = findViewById<TextView>(R.id.tvTextoTarjeta)

            if (estaVolteada) {

                tvTexto.text = listaRepaso[indiceActual].textoPregunta
                tarjetaFlashcard.setBackgroundColor(Color.parseColor("#FFF9C4"))
            } else {

                tvTexto.text = "Respuesta:\n\n" + listaRepaso[indiceActual].respuestaCorrecta
                tarjetaFlashcard.setBackgroundColor(Color.parseColor("#C8E6C9"))
            }
            estaVolteada = !estaVolteada // Cambiamos el estado
        }


        btnSiguiente.setOnClickListener {
            if (indiceActual < listaRepaso.size - 1) {
                indiceActual++
                mostrarTarjeta()
            } else {
                Toast.makeText(this, "¡Repaso terminado!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun mostrarTarjeta() {
        val tvTexto = findViewById<TextView>(R.id.tvTextoTarjeta)
        val tvContador = findViewById<TextView>(R.id.tvContadorRepaso)
        val tarjetaFlashcard = findViewById<LinearLayout>(R.id.tarjetaFlashcard)


        estaVolteada = false
        tvTexto.text = listaRepaso[indiceActual].textoPregunta
        tarjetaFlashcard.setBackgroundColor(Color.parseColor("#FFF9C4")) // Amarillito
        tvContador.text = "Tarjeta ${indiceActual + 1} de ${listaRepaso.size}"
    }
}