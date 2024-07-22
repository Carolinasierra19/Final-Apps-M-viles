package com.example.nuevoproyecto.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.nuevoproyecto.TaskDetailActivity
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.databinding.TaskItemBinding
import com.example.nuevoproyecto.R


class TaskListViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.tvTitle.text = task.title
        binding.tvDescription.text = task.description

        // Apply background color based on task completion status
        if (task.isCompleted) {
            binding.root.setBackgroundResource(R.color.colorCompletedTask)
        } else {
            binding.root.setBackgroundResource(R.color.colorPendingTask)
        }

        binding.root.setOnClickListener {
            val context = binding.root.context
            val intent = Intent(context, TaskDetailActivity::class.java).apply {
                putExtra("TASK", task)
            }
            context.startActivity(intent)
        }
    }
}







