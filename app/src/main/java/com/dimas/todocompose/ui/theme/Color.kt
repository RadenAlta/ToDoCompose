package com.dimas.todocompose.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.core.graphics.luminance

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val LowPriorityColor = Color(0XFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = MediumGray

val ColorScheme.taskItemTextColor: Color
    @Composable
    get() = if (this.isLight) DarkGray else LightGray

val ColorScheme.taskItemBackgroundColor: Color
    @Composable
    get() = if (this.isLight) Color.White else DarkGray

val ColorScheme.fabBackgroundColor: Color
    @Composable
    get() = if (this.isLight) Teal200 else Purple700

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = if (this.isLight) Color.White else LightGray

val ColorScheme.topAppBarBackgroundColor: Color
    @Composable
    get() = if (this.isLight) Purple500 else Color.Black

private val ColorScheme.isLight: Boolean
    get() = this.surface.luminance() > 0.5f
