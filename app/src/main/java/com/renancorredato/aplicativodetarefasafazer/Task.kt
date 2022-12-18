package com.renancorredato.aplicativodetarefasafazer

data class Task(
    val title: String,
    val description: String,
    var done: Boolean =  false
)
