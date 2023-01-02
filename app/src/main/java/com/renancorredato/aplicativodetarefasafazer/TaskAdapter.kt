package com.renancorredato.aplicativodetarefasafazer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.renancorredato.aplicativodetarefasafazer.databinding.ResItemTaskBinding

class TaskAdapter(
    private val onClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

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

        fun bind(
            task: Task,
            onDeleteClick: (Task) -> Unit,
            onClick: (Task) -> Unit
        ) {
            tvTitleTask.text = task.title
            imgBtnDeleteTask.setOnClickListener {
                onDeleteClick(task)
            }
            clTask.setOnClickListener {
                onClick(task)
            }
            if (task.done) {
                tvTitleTask.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
                imgBtnDeleteTask.setImageResource(R.drawable.delete_white)
                clTask.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.success_green
                    )
                )
            } else {
                tvTitleTask.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.black)
                )
                imgBtnDeleteTask.setImageResource(R.drawable.delete_black)
                clTask.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.bright_gray
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ResItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(
            tasks[position],
            onDeleteClick,
            onClick
        )
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun deleteTask(task: Task) {
        val deletePosition = tasks.indexOf(task)
        tasks.remove(task)
        notifyItemRemoved(deletePosition)
    }

    fun updateTask(task: Task) {
        val updatedPosition = tasks.indexOf(task)
        tasks[updatedPosition] = task
        notifyItemRemoved(updatedPosition)
    }
}