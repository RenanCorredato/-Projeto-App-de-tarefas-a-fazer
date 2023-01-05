package com.renancorredato.aplicativodetarefasafazer

import java.io.Serializable

data class Task(
    val title: String,
    val description: String,
    var done: Boolean = false
) : Serializable
