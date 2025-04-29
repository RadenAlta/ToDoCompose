package com.dimas.todocompose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(
    priority: Priority
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PriorityItemPreview() {
    PriorityItem(priority = Priority.HIGH)
}
