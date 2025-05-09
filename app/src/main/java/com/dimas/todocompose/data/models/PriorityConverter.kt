package com.dimas.todocompose.data.models

import androidx.room.TypeConverter

class PriorityConverter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}
