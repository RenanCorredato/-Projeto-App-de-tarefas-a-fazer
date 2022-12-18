package com.renancorredato.aplicativodetarefasafazer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.renancorredato.aplicativodetarefasafazer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()

        setupLayout()
    }

    private fun setupLayout() {
        binding.fabNewTask.setOnClickListener {

            val rand = (1..1_000).random()

            adapter.addTask(
                Task(
                    title = "Titulo $rand",
                    description = "Descrição"
                )
            )
        }
    }

    private fun setupAdapter() {

        adapter = TaskAdapter { task ->
            adapter.deleteTask(task)
        }
        binding.rvTasks.adapter = adapter
    }
}