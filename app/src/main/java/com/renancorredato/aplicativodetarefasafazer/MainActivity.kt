package com.renancorredato.aplicativodetarefasafazer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.renancorredato.aplicativodetarefasafazer.databinding.ActivityMainBinding
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater

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
        adapter = TaskAdapter(
            onDeleteClick = { taskToConfirmDeletion ->
                showDeleteConfirmation(taskToConfirmDeletion) { taskToBeDeleted ->
                    adapter.deleteTask(taskToBeDeleted)
                }
            },
            onClick = { taskToBeShowed ->
                showTaskDetails(taskToBeShowed) { taskToBeUpdated ->
                    adapter.updateTask(taskToBeUpdated)
                }
            }
        )

        binding.rvTasks.adapter = adapter
    }

    private fun showTaskDetails(task: Task, onTaskStatusChanged: (Task) -> Unit) {
        val build = AlertDialog.Builder(this)
        build.apply {
            setTitle("Detalhes da tarefa")
            setMessage(
                """
                    Titulo: ${task.title}
                    Descrição: ${task.description}
                    Concluida: ${
                    if (task.done)
                        "Sim"
                    else
                        "Não"
                }
                    
                """.trimIndent()
            )
            setPositiveButton(
                if (task.done)
                    "Não concluida"
                else
                    "Concluida"
            ) { _, _ ->
                task.done = !task.done
            }
            setNegativeButton("Fechar") { dialog, _ ->
                dialog.dismiss()
            }
        }
        build.show()
    }

    private fun showDeleteConfirmation(task: Task, onConfirm: (Task) -> Unit) {
        val build = AlertDialog.Builder(this)
        build.apply {
            setTitle("Confirmação")
            setMessage("Deseja excluir a tarefa \"${task.title}\"?")
            setPositiveButton("Sim") { _, _ ->
                onConfirm(task)
            }
            setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }
        }
        build.show()
    }
}