package com.jay30.composableeffectsdemo.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay30.composableeffectsdemo.MainScreenEnum
import com.jay30.composableeffectsdemo.frame.DisposableEffectDemoScreen
import com.jay30.composableeffectsdemo.frame.LaunchedEffectDemoScreen
import com.jay30.composableeffectsdemo.frame.SideEffectDemoScreen
import com.jay30.composableeffectsdemo.mvvm.viewmodel.DataViewModel

@Composable
fun MainScreen(
    dataViewModel: DataViewModel = viewModel(),
) {
    val navController = rememberNavController()
    Scaffold(
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MainScreenEnum.LaunchedEffectDemoScreen.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                composable(route = MainScreenEnum.LaunchedEffectDemoScreen.name) {
                    LaunchedEffectDemoScreen(dataViewModel,navController)
                }
                composable(route = MainScreenEnum.DisposableEffectDemoScreen.name) {
                    DisposableEffectDemoScreen(dataViewModel,navController)
                }
                composable(route = MainScreenEnum.SideEffectDemoScreen.name) {
                    SideEffectDemoScreen(dataViewModel,navController)
                }
            }

        }
    )
}