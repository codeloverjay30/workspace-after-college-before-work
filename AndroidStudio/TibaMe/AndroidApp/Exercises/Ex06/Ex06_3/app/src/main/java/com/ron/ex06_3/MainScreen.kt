package com.ron.ex06_3

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screen.Login.name) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Products.name) {
            ProductsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Main()
}