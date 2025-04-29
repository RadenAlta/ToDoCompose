package com.dimas.todocompose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.dimas.todocompose.R
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.dimas.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var parentSize by remember { mutableStateOf(IntSize.Zero) }
    val angle by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { parentSize = it.size }
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
        ) {
            drawCircle(color = priority.color)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = priority.name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        IconButton(
            modifier = Modifier.rotate(angle),
            onClick = { expanded = !expanded }
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(R.string.drop_down_icon),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { parentSize.width.toDp() })
        ) {
            Priority.values().forEach { priorityOption ->
                DropdownMenuItem(
                    text = { Text(priorityOption.name) },
                    onClick = {
                        expanded = false
                        onPrioritySelected(priorityOption)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPriorityDropDown() {
    PriorityDropDown(
        priority = Priority.HIGH,
        onPrioritySelected = {}
    )
}
