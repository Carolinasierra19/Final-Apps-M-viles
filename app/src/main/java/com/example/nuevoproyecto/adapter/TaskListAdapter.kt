package com.example.nuevoproyecto.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nuevoproyecto.R
import com.example.nuevoproyecto.TaskDetailActivity
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.databinding.TaskItemBinding


class TaskListAdapter(
    private val onDeleteClick: (Task) -> Unit
) : ListAdapter<Task, TaskListAdapter.TaskListViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater, parent, false)
        return TaskListViewHolder(binding, onDeleteClick)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    class TaskListViewHolder(
        private val binding: TaskItemBinding,
        private val onDeleteClick: (Task) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description

            // Apply background color based on task completion status
            if (task.isCompleted) {
                binding.root.setBackgroundResource(R.color.colorCompletedTask)
            } else {
                binding.root.setBackgroundResource(R.color.colorPendingTask)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(task)
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

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }
}




