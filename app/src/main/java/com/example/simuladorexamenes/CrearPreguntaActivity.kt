package com.example.simuladorexamenes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CrearPreguntaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_pregunta)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val etPregunta = findViewById<EditText>(R.id.etPregunta)
        val etCorrecta = findViewById<EditText>(R.id.etCorrecta)
        val etDist1 = findViewById<EditText>(R.id.etDistractor1)
        val etDist2 = findViewById<EditText>(R.id.etDistractor2)
        val etDist3 = findViewById<EditText>(R.id.etDistractor3)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        btnVolver.setOnClickListener {
            finish()
        }

        btnGuardar.setOnClickListener {

            val textoPregunta = etPregunta.text.toString()
            val correcta = etCorrecta.text.toString()
            val d1 = etDist1.text.toString()
            val d2 = etDist2.text.toString()
            val d3 = etDist3.text.toString()


            if (textoPregunta.isNotEmpty() && correcta.isNotEmpty() && d1.isNotEmpty() && d2.isNotEmpty() && d3.isNotEmpty()) {


                val nuevaPregunta = Pregunta(textoPregunta, correcta, d1, d2, d3)


                RepositorioPreguntas.listaPreguntas.add(nuevaPregunta)


                Toast.makeText(this, "Pregunta guardada con éxito", Toast.LENGTH_SHORT).show()


                finish()
            } else {

                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}