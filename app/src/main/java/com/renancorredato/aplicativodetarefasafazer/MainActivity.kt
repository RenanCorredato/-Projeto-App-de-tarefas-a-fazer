package com.renancorredato.aplicativodetarefasafazer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.renancorredato.aplicativodetarefasafazer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = TaskAdapter()

        binding.rvTasks.adapter = adapter

        adapter.addTask(
            Task(
                title = "Titulo",
                description = "Descrição"
            )
        )
    }
}