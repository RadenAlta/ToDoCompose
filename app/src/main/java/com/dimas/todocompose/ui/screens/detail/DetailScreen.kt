package com.dimas.todocompose.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimas.todocompose.data.ToDoTask
import com.dimas.todocompose.data.models.Priority

@Composable
fun DetailScreen(
    task: ToDoTask,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = task.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Priority: ${task.priority.name}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = task.description,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = navigateBack) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        task = ToDoTask(
            id = 1,
            title = "Sample Task",
            description = "This is a sample description for preview.",
            priority = Priority.HIGH
        ),
        navigateBack = {}
    )
}
