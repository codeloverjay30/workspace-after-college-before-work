package com.jay30.composablenavigationdemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposableNavigationDemoScreen(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreenEnum.MAIN_FRAME.name
    ) {
        composable(
            route = MainScreenEnum.MAIN_FRAME.name
        ){
            MainFrame(navController)
        }

        composable(
            route = MainScreenEnum.PROFILE_FRAME.name
        ){
            ProfileFrame(
                onNavigationEvent = { navController.navigate(route = MainScreenEnum.SEXY_STORE.name ) },
            )
        }

    }
}