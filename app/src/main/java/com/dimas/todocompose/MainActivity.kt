package com.dimas.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dimas.todocompose.navigation.SetupNavigation
import com.dimas.todocompose.ui.theme.ToDoComposeTheme
import com.dimas.todocompose.ui.screens.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ToDoComposeTheme {
                navController = rememberNavController()
                SetupNavigation(
                    navHostController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}