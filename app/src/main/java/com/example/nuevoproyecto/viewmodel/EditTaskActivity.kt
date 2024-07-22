package com.example.nuevoproyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.databinding.ActivityEditTaskBinding
import com.example.nuevoproyecto.viewmodel.TaskListViewModel

class EditTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding
    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskListViewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        // Obtener el parcelable extra
        task = intent.getParcelableExtra<Task>("TASK") ?: Task("", "", false, emptyList())

        // Asignar los valores obtenidos a los campos de texto
        binding.etTitle.setText(task.title)
        binding.etDescription.setText(task.description)

        // Configurar el botón de guardado
        binding.btnSave.setOnClickListener {
            task.title = binding.etTitle.text.toString()
            task.description = binding.etDescription.text.toString()
            taskListViewModel.updateTask(task)
            finish()
        }
    }
}





