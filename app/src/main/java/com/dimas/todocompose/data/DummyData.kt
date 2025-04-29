package com.dimas.todocompose.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.data.models.Task
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
val dummyTaskList = listOf(
    Task(1, "Belajar Kotlin", "Pelajari dasar-dasar Kotlin", Priority.HIGH, dueDate = LocalDateTime.now()),
    Task(2, "Membaca Buku", "Baca buku Android", Priority.MEDIUM, dueDate = LocalDateTime.now()),
    Task(3, "Olahraga", "Jogging 30 menit", Priority.LOW, dueDate = LocalDateTime.now()),
    Task(4, "Belajar Compose", "Pelajari Jetpack Compose", Priority.HIGH, dueDate = LocalDateTime.now()),
    Task(5, "Menulis Blog", "Tulis pengalaman Kotlin", Priority.MEDIUM, dueDate = LocalDateTime.now()),
    Task(6, "Mengerjakan PR", "Tugas Matematika", Priority.HIGH, dueDate = LocalDateTime.now()),
    Task(7, "Tonton Tutorial", "Belajar Android Studio", Priority.LOW, dueDate = LocalDateTime.now()),
    Task(8, "Membuat Aplikasi", "Proyek to-do list", Priority.HIGH, dueDate = LocalDateTime.now()),
    Task(9, "Belajar Git", "Dasar Git & GitHub", Priority.MEDIUM, dueDate = LocalDateTime.now()),
    Task(10, "Bersih-bersih", "Rapikan meja belajar", Priority.LOW, dueDate = LocalDateTime.now())
)
