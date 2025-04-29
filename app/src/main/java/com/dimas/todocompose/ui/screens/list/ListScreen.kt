package com.dimas.todocompose.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.dimas.todocompose.ui.screens.viewmodel.SharedViewModel
import com.dimas.todocompose.ui.theme.fabBackgroundColor
import com.dimas.todocompose.ui.util.Action
import com.dimas.todocompose.ui.util.SearchAppBarState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    action: Action,
    navigateToTaskScreen: (taskID: Int) -> Unit,
    navigateToDetailScreen: (taskID: Int) -> Unit,
    navigateToAboutScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val searchAppBarState by sharedViewModel.searchAppBarState.collectAsState()
    val searchTextState by sharedViewModel.searchTextState.collectAsState()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = action) {
        sharedViewModel.handleDatabaseActions(action)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                searchAppBarState = searchAppBarState,
                lowPriorityTasks = sharedViewModel.lowPriorityTasks.collectAsState().value,
                highPriorityTasks = sharedViewModel.highPriorityTasks.collectAsState().value,
                sortState = sortState,
                navigateToTaskScreen = { taskId -> navigateToTaskScreen(taskId) },
                onSwipeToDelete = { action, task ->
                    sharedViewModel.updateAction(action)
                    sharedViewModel.updateTaskFields(selectedTask = task)
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    }
                }
            )
        },
        floatingActionButton = {
            ListFab {
                navigateToTaskScreen(-1)
            }
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = onFabClicked,
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}
