package com.example.nuevoproyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nuevoproyecto.adapter.ItemListAdapter
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.databinding.ActivityTaskDetailBinding
import com.example.nuevoproyecto.viewmodel.TaskListViewModel

class TaskDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDetailBinding
    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var task: Task
    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskListViewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)

        task = intent.getParcelableExtra("TASK") ?: Task("", "", false, emptyList())
        binding.tvTitle.text = task.title
        binding.tvDescription.text = task.description

        initRecyclerView()

        binding.btnMarkAsCompleted.setOnClickListener {
            task.isCompleted = true
            taskListViewModel.updateTask(task)
            finish()
        }

        binding.btnEditTask.setOnClickListener {
            val intent = Intent(this, EditTaskActivity::class.java).apply {
                putExtra("TASK", task)
            }
            startActivity(intent)
        }

        binding.btnBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        itemListAdapter = ItemListAdapter()
        binding.rvItemsHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvItemsHorizontal.adapter = itemListAdapter

        itemListAdapter.submitList(task.itemsNeeded)
    }
}




