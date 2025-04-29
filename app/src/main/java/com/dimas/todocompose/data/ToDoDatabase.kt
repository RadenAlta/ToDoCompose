package com.dimas.todocompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dimas.todocompose.data.models.PriorityConverter

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
@TypeConverters(PriorityConverter::class)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
