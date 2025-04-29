package com.dimas.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dimas.todocompose.navigation.destination.listComposable
import com.dimas.todocompose.navigation.destination.taskComposable
import com.dimas.todocompose.navigation.destination.detailComposable
import com.dimas.todocompose.navigation.destination.aboutComposable
import com.dimas.todocompose.ui.util.Constants.LIST_SCREEN
import com.dimas.todocompose.ui.screens.viewmodel.SharedViewModel

@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController)
    }

    NavHost(
        navController = navHostController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.list,
            navigateToDetailScreen = screen.detail,
            navigateToAboutScreen = screen.about,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        detailComposable(
            navigateBack = { navHostController.popBackStack() }
        )
        aboutComposable(
            navigateBack = { navHostController.popBackStack() }
        )
    }
}
