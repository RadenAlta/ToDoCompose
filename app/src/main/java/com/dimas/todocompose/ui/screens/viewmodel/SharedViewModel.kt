package com.dimas.todocompose.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimas.todocompose.data.ToDoTask
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.data.repository.ToDoRepository
import com.dimas.todocompose.ui.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.catch

class SharedViewModel(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val tasks: StateFlow<RequestState<List<ToDoTask>>> = _tasks

    private val _searchedTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val searchedTasks: StateFlow<RequestState<List<ToDoTask>>> = _searchedTasks

    private val _selectedTask = MutableStateFlow<ToDoTask?>(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    init {
        getAllTasks()
    }

    // Function to get all tasks
    fun getAllTasks() {
        _tasks.value = RequestState.Loading
        viewModelScope.launch {
            try {
                repository.getAllTasks.collect { taskList ->
                    _tasks.value = RequestState.Success(taskList)
                }
            } catch (e: Exception) {
                _tasks.value = RequestState.Error(e)
            }
        }
    }

    // Function to search tasks based on a query
    fun searchTasks(query: String) {
        _searchedTasks.value = RequestState.Loading
        viewModelScope.launch {
            try {
                repository.searchDatabase(query).collect { taskList ->
                    _searchedTasks.value = RequestState.Success(taskList)
                }
            } catch (e: Exception) {
                _searchedTasks.value = RequestState.Error(e)
            }
        }
    }

    // Function to add a new task
    fun addTask(title: String, description: String, priority: Priority) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTask = ToDoTask(title = title, description = description, priority = priority)
            repository.addTask(newTask)
        }
    }

    // Function to update an existing task
    fun updateTask(task: ToDoTask) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    // Function to delete a specific task
    fun deleteTask(task: ToDoTask) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    // Function to delete all tasks
    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    // Function to get a task by its ID
    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            try {
                repository.getSelectedTask(taskId).collect {
                    _selectedTask.value = it
                }
            } catch (e: Exception) {
                _selectedTask.value = null
            }
        }
    }
}
