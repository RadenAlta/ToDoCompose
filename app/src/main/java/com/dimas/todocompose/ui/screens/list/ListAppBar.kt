package com.dimas.todocompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.dimas.todocompose.R
import com.dimas.todocompose.components.DisplayAlertDialog
import com.dimas.todocompose.components.PriorityItem
import com.dimas.todocompose.ui.theme.LARGE_PADDING
import com.dimas.todocompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.dimas.todocompose.ui.util.Action
import com.dimas.todocompose.data.models.Priority
import com.dimas.todocompose.ui.util.SearchAppBarState
import com.dimas.todocompose.ui.screens.viewmodel.SharedViewModel

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.updateAppbarState(SearchAppBarState.OPENED)
                },
                onSortClicked = { priority ->
                    sharedViewModel.persistSortState(priority)
                },
                onDeleteAllConfirmed = {
                    sharedViewModel.updateAction(Action.DELETE_ALL)
                }
            )
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.updateSearchTextState(newText)
                },
                onCloseClicked = {
                    sharedViewModel.updateAppbarState(SearchAppBarState.CLOSED)
                    sharedViewModel.updateSearchTextState("")
                },
                onSearchClicked = {
                    sharedViewModel.searchTasks(it)
                }
            )
        }
    }
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.list_screen_title),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            ListAppBarAction(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllConfirmed = onDeleteAllConfirmed
            )
        }
    )
}

@Composable
fun ListAppBarAction(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_all_task),
        message = stringResource(id = R.string.delete_all_task_confirmation),
        openDialog = openDialog,
        closeDialog = { onDeleteAllConfirmed() }
    )

    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteAllConfirmed = { openDialog = true })
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_action),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = { expended = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_action),
            tint = MaterialTheme.colorScheme.onSurface
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            for (priority in Priority.values().filter { it != Priority.MEDIUM }) {
                DropdownMenuItem(onClick = {
                    expended = false
                    onSortClicked(priority)
                }) {
                    PriorityItem(priority = priority)
                }
            }
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllConfirmed: () -> Unit
) {
    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = { expended = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.delete_all_action),
            tint = MaterialTheme.colorScheme.onSurface
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(onClick = {
                expended = false
                onDeleteAllConfirmed()
            }) {
                Text(
                    text = stringResource(R.string.delete_all_action),
                    modifier = Modifier.padding(start = LARGE_PADDING),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(R.string.search_place_holder),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    when {
                        text.isNotEmpty() -> onTextChange("")
                        else -> onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteAllConfirmed = {}
    )
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "",
        onTextChange = {},
        onCloseClicked = {}
    ) {

    }
}
