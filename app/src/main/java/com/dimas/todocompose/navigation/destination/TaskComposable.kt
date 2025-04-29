package com.dimas.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dimas.todocompose.ui.screens.task.TaskScreen
import com.dimas.todocompose.ui.util.Constants.TASK_ARGUMENT_KEY
import com.dimas.todocompose.ui.util.Constants.TASK_SCREEN
import com.dimas.todocompose.data.ToDoTask
import com.dimas.todocompose.ui.screens.viewmodel.SharedViewModel

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (String) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = "$TASK_SCREEN/{$TASK_ARGUMENT_KEY}",
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            defaultValue = -1
        })
    ) { navBackStackEntry ->

        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY) ?: -1

        val selectedTask = sharedViewModel.getTaskById(taskId)

        TaskScreen(
            sharedViewModel = sharedViewModel,
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}
