package com.dimas.todocompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimas.todocompose.ui.theme.TOP_APP_BAR_HEIGHT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        color = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 6.dp
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = "Cari...",
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        text = "",
        onTextChange = {},
        onSearchClicked = {}
    )
}
