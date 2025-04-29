package com.dimas.todocompose.data.models

import androidx.compose.ui.graphics.Color
import com.dimas.todocompose.ui.theme.HighPriorityColor
import com.dimas.todocompose.ui.theme.LowPriorityColor
import com.dimas.todocompose.ui.theme.MediumPriorityColor
import com.dimas.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
