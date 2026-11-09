package com.example.simuladorexamenes

object RepositorioPreguntas {

    val listaPreguntas = mutableListOf<Pregunta>(
        Pregunta("¿Qué puerto estándar utiliza el protocolo HTTPS?", "443", "80", "8080", "22"),
        Pregunta("En bases de datos relacionales, ¿qué comando se usa para extraer información?", "SELECT", "GET", "EXTRACT", "PULL"),
        Pregunta("¿Qué significa las siglas IoT?", "Internet of Things", "Input Output Transfer", "Internal Object Tree", "Internet of Technology")
    )
}