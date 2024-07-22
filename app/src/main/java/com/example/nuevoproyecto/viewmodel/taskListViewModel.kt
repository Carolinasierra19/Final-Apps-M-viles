package com.example.nuevoproyecto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nuevoproyecto.data.Task
import com.example.nuevoproyecto.data.TaskList

class TaskListViewModel : ViewModel() {
    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> = _taskList

    init {
        _taskList.value = TaskList.listOfTasks
    }

    fun addTask(title: String, description: String) {
        val task = Task(title, description)
        val updatedList = _taskList.value.orEmpty().toMutableList()
        updatedList.add(task)
        _taskList.value = updatedList
    }

    fun updateTask(task: Task) {
        val updatedList = _taskList.value.orEmpty().toMutableList()
        val index = updatedList.indexOfFirst { it.title == task.title && it.description == task.description }
        if (index != -1) {
            updatedList[index] = task
        }
        _taskList.value = updatedList
    }

    fun deleteTask(task: Task) {
        val updatedList = _taskList.value.orEmpty().toMutableList()
        updatedList.remove(task)
        _taskList.value = updatedList
    }

    fun moveTaskToCompleted(task: Task) {
        task.isCompleted = true
        updateTask(task)
    }
}




