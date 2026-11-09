package com.example.simuladorexamenes

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExamenActivity : AppCompatActivity() {


    private var indiceActual = 0
    private var aciertos = 0
    private lateinit var listaExamen: List<Pregunta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_examen)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            finish()
        }


        listaExamen = RepositorioPreguntas.listaPreguntas.shuffled()


        if (listaExamen.isEmpty()) {
            Toast.makeText(this, "Primero crea algunas tarjetas de estudio", Toast.LENGTH_LONG).show()
            finish()
            return
        }


        cargarPregunta()
    }

    private fun cargarPregunta() {
        val tvPregunta = findViewById<TextView>(R.id.tvPregunta)
        val btnOpcion1 = findViewById<Button>(R.id.btnOpcion1)
        val btnOpcion2 = findViewById<Button>(R.id.btnOpcion2)
        val btnOpcion3 = findViewById<Button>(R.id.btnOpcion3)
        val btnOpcion4 = findViewById<Button>(R.id.btnOpcion4)

        val preguntaActual = listaExamen[indiceActual]


        tvPregunta.text = preguntaActual.textoPregunta


        val opciones = listOf(
            preguntaActual.respuestaCorrecta,
            preguntaActual.distractor1,
            preguntaActual.distractor2,
            preguntaActual.distractor3
        ).shuffled()


        btnOpcion1.text = opciones[0]
        btnOpcion2.text = opciones[1]
        btnOpcion3.text = opciones[2]
        btnOpcion4.text = opciones[3]


        btnOpcion1.setOnClickListener { evaluarRespuesta(btnOpcion1.text.toString(), preguntaActual.respuestaCorrecta) }
        btnOpcion2.setOnClickListener { evaluarRespuesta(btnOpcion2.text.toString(), preguntaActual.respuestaCorrecta) }
        btnOpcion3.setOnClickListener { evaluarRespuesta(btnOpcion3.text.toString(), preguntaActual.respuestaCorrecta) }
        btnOpcion4.setOnClickListener { evaluarRespuesta(btnOpcion4.text.toString(), preguntaActual.respuestaCorrecta) }
    }

    private fun evaluarRespuesta(seleccion: String, correcta: String) {

        if (seleccion == correcta) {
            aciertos++
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrecto. Era: $correcta", Toast.LENGTH_SHORT).show()
        }


        indiceActual++

        if (indiceActual < listaExamen.size) {
            cargarPregunta()
        } else {

            Toast.makeText(this, "Examen terminado. Calificación: $aciertos/${listaExamen.size}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}