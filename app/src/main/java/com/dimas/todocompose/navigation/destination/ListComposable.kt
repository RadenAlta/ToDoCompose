package com.dimas.todocompose.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dimas.todocompose.ui.screens.list.ListScreen
import com.dimas.todocompose.ui.screens.viewmodel.SharedViewModel
import com.dimas.todocompose.ui.util.Action
import com.dimas.todocompose.ui.util.Constants.LIST_ARGUMENT_KEY
import com.dimas.todocompose.ui.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    navigateToDetailScreen: (Int) -> Unit,
    navigateToAboutScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = "$LIST_SCREEN/{$LIST_ARGUMENT_KEY}",
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            defaultValue = Action.NO_ACTION.name
        })
    ) { navBackStackEntry ->
        val action = Action.valueOf(
            navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY) ?: Action.NO_ACTION.name
        )

        ListScreen(
            action = action,
            navigateToTaskScreen = navigateToTaskScreen,
            navigateToDetailScreen = navigateToDetailScreen,
            navigateToAboutScreen = navigateToAboutScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
