package com.dimas.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dimas.todocompose.data.ToDoTask
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.ui.screens.detail.DetailScreen
import com.dimas.todocompose.ui.util.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.detailComposable(
    navigateBack: () -> Unit
) {
    composable(
        route = "detail/{$TASK_ARGUMENT_KEY}",
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            defaultValue = -1
        })
    ) { backStackEntry ->
        val taskId = backStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY) ?: -1

        val dummyTask = ToDoTask(
            id = taskId,
            title = "Sample Task $taskId",
            description = "This is a dummy description for task $taskId",
            priority = Priority.HIGH
        )

        DetailScreen(
            task = dummyTask,
            navigateBack = navigateBack
        )
    }
}
