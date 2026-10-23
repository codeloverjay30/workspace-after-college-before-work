package com.jay30.dialogdestinationdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Settings

@Composable
fun HomeScreen(onNavigateToSettings: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Text("Home")
            Button(onClick = onNavigateToSettings) {
                Text("Open settings")
            }
        }
    }
}

// This screen will be displayed as a dialog
@Composable
fun SettingsScreen() {
    Text("Settings")
    // ...
}

@Composable
fun DialogDestinationDemoFrame() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Home) {
        composable<Home> { HomeScreen(onNavigateToSettings = { navController.navigate(route = Settings) }) }
        dialog<Settings> { SettingsScreen() }
    }
}