package com.dimas.todocompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,
    surface = DarkGray,
    background = DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = LightGray,
    onBackground = LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = Purple500,
    secondary = Teal200,
    surface = Color.White,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = DarkGray,
    onBackground = DarkGray
)

@Composable
fun ToDoComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
