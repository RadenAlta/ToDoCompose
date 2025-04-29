package com.dimas.todocompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: LocalDateTime,
    val isCompleted: Boolean = false
)
