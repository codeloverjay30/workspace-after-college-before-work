package com.example.healthhelper.dietary.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay30.testproject3.dietary.enumclass.DietDiaryScreenEnum
import com.example.healthhelper.dietary.frame.DietDiaryMainFrame
import com.jay30.testproject3.dietary.frame.DietDiaryMealFrame
import com.example.healthhelper.dietary.viewmodel.MealsOptionViewModel

@Composable
fun DietDiaryMainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val TAG = "tag_DietDiaryMainScreen"
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DietDiaryScreenEnum.DietDiaryMainFrame.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            composable(
                route = DietDiaryScreenEnum.DietDiaryMainFrame.name
            ) {
                DietDiaryMainFrame(
                    navController = navController
                )
            }

            composable(
                route = DietDiaryScreenEnum.DietDiaryMealFrame.name
            ) {
                DietDiaryMealFrame(
                    navController = navController,
                )
            }
        }
    }
}