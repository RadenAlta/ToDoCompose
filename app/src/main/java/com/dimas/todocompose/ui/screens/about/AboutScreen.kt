package com.dimas.todocompose.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimas.todocompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.me),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Nama: Raden Dimas Taufik Rahmat", style = MaterialTheme.typography.titleMedium)
                Text(text = "Email: radendimastaufikrahmat@gmail.com", style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navigateBack = {})
}
