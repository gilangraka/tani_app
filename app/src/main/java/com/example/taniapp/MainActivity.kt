package com.example.taniapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taniapp.ui.screens.HomeScreen
import com.example.taniapp.ui.screens.LoginScreen
import com.example.taniapp.ui.screens.RegisterScreen
import com.example.taniapp.ui.screens.WelcomeScreen
import com.example.taniapp.ui.theme.TaniAppTheme
import com.example.taniapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            TaniAppTheme {
                NavHost(navController = navController, startDestination = "welcome") {
                    composable("welcome") { WelcomeScreen(navController = navController) }
                    composable("signin") { LoginScreen(navController = navController, authViewModel = authViewModel) }
                    composable("signup") { RegisterScreen(navController = navController, authViewModel = authViewModel) }
                    composable("home") { HomeScreen(navController = navController, authViewModel = authViewModel) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    TaniAppTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}