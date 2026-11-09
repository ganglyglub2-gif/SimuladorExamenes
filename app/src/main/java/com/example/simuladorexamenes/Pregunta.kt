package com.example.simuladorexamenes

import java.io.Serializable

data class Pregunta(
    val textoPregunta: String,
    val respuestaCorrecta: String,
    val distractor1: String,
    val distractor2: String,
    val distractor3: String
) : Serializable