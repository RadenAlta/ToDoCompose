package com.dimas.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dimas.todocompose.ui.screens.about.AboutScreen

fun NavGraphBuilder.aboutComposable(
    navigateBack: () -> Unit
) {
    composable(route = "about") {
        AboutScreen(
            navigateBack = navigateBack
        )
    }
}
