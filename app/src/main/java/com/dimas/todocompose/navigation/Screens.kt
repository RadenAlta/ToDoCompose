package com.dimas.todocompose.navigation

import androidx.navigation.NavHostController
import com.dimas.todocompose.ui.util.Constants.LIST_SCREEN

class Screens(navHostController: NavHostController) {

    val list: (Int) -> Unit = { taskId ->
        navHostController.navigate("task/$taskId")
    }

    val task: (String) -> Unit = { action ->
        navHostController.navigate("list/$action") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val detail: (Int) -> Unit = { taskId ->
        navHostController.navigate("detail/$taskId")
    }

    val about: () -> Unit = {
        navHostController.navigate("about")
    }
}
