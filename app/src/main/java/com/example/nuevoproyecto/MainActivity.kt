package com.example.nuevoproyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nuevoproyecto.adapter.TaskListAdapter
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.databinding.ActivityMainBinding
import com.example.nuevoproyecto.databinding.DialogTaskBinding
import com.example.nuevoproyecto.viewmodel.TaskListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogTaskBinding: DialogTaskBinding
    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var verticalAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogTaskBinding = DialogTaskBinding.inflate(layoutInflater)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogTaskBinding.root)
        val alertDialog = alertDialogBuilder.create()

        setContentView(binding.root)

        // Initialize the ViewModel
        taskListViewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        // Initialize the RecyclerView and the Adapters
        initRecyclerView()

        // Observe the LiveData from the ViewModel
        taskListViewModel.taskList.observe(this) { tasks ->
            tasks?.let {
                verticalAdapter.submitList(it)
            }
        }

        setListener(alertDialog)
    }

    private fun initRecyclerView() {
        // Initialize vertical RecyclerView
        verticalAdapter = TaskListAdapter(
            onDeleteClick = { task -> showDeleteTaskDialog(task) }
        )
        binding.rvTasksVertical.layoutManager = LinearLayoutManager(this)
        binding.rvTasksVertical.adapter = verticalAdapter
    }

    private fun setListener(alertDialog: AlertDialog) {
        binding.fabAddTask.setOnClickListener {
            showAlertDialog(alertDialog)
        }
        binding.btnViewCompletedTasks.setOnClickListener {
            val intent = Intent(this, CompletedTasksActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        alertDialog.show()
        dialogTaskBinding.fabPlus.setOnClickListener {
            val title = addTitleTask()
            val description = addDescriptionTask()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                taskListViewModel.addTask(title, description)
                alertDialog.dismiss()
            }
        }
    }

    private fun showDeleteTaskDialog(task: Task) {
        AlertDialog.Builder(this)
            .setTitle("Delete Task")
            .setMessage("Are you sure you want to delete this task?")
            .setPositiveButton("Yes") { _, _ ->
                taskListViewModel.deleteTask(task)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun addTitleTask(): String {
        return dialogTaskBinding.etTitle.text.toString()
    }

    private fun addDescriptionTask(): String {
        return dialogTaskBinding.etDescription.text.toString()
    }
}



