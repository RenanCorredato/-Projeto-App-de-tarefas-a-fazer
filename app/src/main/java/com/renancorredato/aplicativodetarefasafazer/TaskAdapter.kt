package com.renancorredato.aplicativodetarefasafazer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.renancorredato.aplicativodetarefasafazer.databinding.ResItemTaskBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    inner class TaskViewHolder(
        itemView: ResItemTaskBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        private val tvTitleTask: TextView
        private val imgBtnDeleteTask: ImageButton
        private val clTask: ConstraintLayout

        init {
            tvTitleTask = itemView.tvTitleTask
            imgBtnDeleteTask = itemView.imgBtnDeleteTask
            clTask = itemView.clTask
        }

        fun bind(task: Task) {
            tvTitleTask.text = task.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ResItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(
            tasks[position]
        )
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
}