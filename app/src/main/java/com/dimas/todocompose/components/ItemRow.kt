package com.dimas.todocompose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.data.ToDoTask
import com.dimas.todocompose.ui.theme.LARGE_PADDING
import com.dimas.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.dimas.todocompose.ui.theme.TASK_ITEM_ELEVATION
import com.dimas.todocompose.ui.theme.taskItemBackgroundColor
import com.dimas.todocompose.ui.theme.taskItemTextColor

@Composable
fun ItemRow(
    task: ToDoTask,
    onItemClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(task.id) },
        color = MaterialTheme.colorScheme.surface,
        shape = RectangleShape,
        tonalElevation = TASK_ITEM_ELEVATION
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(8f),
                    text = task.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = task.priority.color)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = task.description,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemRowPreview() {
    ItemRow(
        task = ToDoTask(
            id = 1,
            title = "Belajar Compose",
            description = "Mempelajari dasar-dasar Jetpack Compose.",
            priority = Priority.HIGH
        ),
        onItemClick = {}
    )
}
