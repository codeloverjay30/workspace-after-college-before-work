package com.tibame.tip101.exercise.exer6_03

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.name,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable(route = Screen.LoginScreen.name) {
            LoginScreen(
                navController = navController
            )
        }
        composable(route = Screen.DeviceScreen.name) {
            DeviceScreen()
        }
    }
}