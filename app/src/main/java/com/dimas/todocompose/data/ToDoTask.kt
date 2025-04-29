package com.dimas.todocompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.data.models.PriorityConverter
import com.dimas.todocompose.ui.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
@TypeConverters(PriorityConverter::class)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
