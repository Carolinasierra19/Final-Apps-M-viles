package com.example.nuevoproyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nuevoproyecto.adapter.TaskListAdapter
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.databinding.ActivityCompletedTasksBinding
import com.example.nuevoproyecto.viewmodel.TaskListViewModel

class CompletedTasksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompletedTasksBinding
    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompletedTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskListViewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        // Set listener for going back to the main activity
        binding.btnBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        initRecyclerView()

        // Observe the completed task list from the ViewModel
        taskListViewModel.taskList.observe(this) { tasks ->
            val completedTasks = tasks.filter { it.isCompleted }
            adapter.submitList(completedTasks)
        }
    }

    private fun initRecyclerView() {
        adapter = TaskListAdapter(
            onDeleteClick = { task -> showDeleteTaskDialog(task) }
        )
        binding.rvCompletedTasks.layoutManager = LinearLayoutManager(this)
        binding.rvCompletedTasks.adapter = adapter
    }

    private fun showDeleteTaskDialog(task: Task) {
        // Implement the deletion dialog
    }
}
