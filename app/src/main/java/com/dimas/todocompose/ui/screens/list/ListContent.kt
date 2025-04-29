package com.dimas.todocompose.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material3.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.dimas.todocompose.R
import com.dimas.todocompose.data.ToDoTask
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.ui.theme.*
import com.dimas.todocompose.ui.util.Action
import com.dimas.todocompose.ui.util.RequestState
import com.dimas.todocompose.ui.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    searchAppBarState: SearchAppBarState,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    navigateToTaskScreen: (taskID: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = allTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete
                    )
                }
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = onSwipeToDelete
                )
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = onSwipeToDelete
                )
            }
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskID: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = onSwipeToDelete
        )
    }
}

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Icon(
            modifier = Modifier.rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskID: Int) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            val dismissState = rememberDismissState()

            val dismissDirection = dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                val scope = rememberCoroutineScope()

                LaunchedEffect(key1 = true) {
                    scope.launch {
                        delay(300)
                        onSwipeToDelete(Action.DELETE, task)
                    }
                }
            }

            val degrees by animateFloatAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> 0f
                    else -> -45f
                }
            )

            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && !isDismissed,
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                    background = { RedBackground(degrees = degrees) },
                    dismissContent = {
                        TaskItem(toDoTask = task, navigateToTaskScreen = navigateToTaskScreen)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RectangleShape,
        elevation = 2.dp,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }) {
        Column(
            modifier = Modifier
                .padding(all = LARGEST_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(10f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                text = toDoTask.description,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTaskItem() {
    TaskItem(toDoTask = ToDoTask(
        id = 1,
        title = "Title",
        description = "new description",
        priority = Priority.HIGH
    ), navigateToTaskScreen = {})
}
