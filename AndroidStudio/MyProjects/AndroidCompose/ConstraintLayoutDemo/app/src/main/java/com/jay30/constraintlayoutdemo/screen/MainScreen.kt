package com.jay30.constraintlayoutdemo.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay30.constraintlayoutdemo.frame.ConstraintLayoutDemoFrame
import com.jay30.constraintlayoutdemo.frame.ConstraintLayoutGuidelinesDemoFrame
import com.jay30.constraintlayoutdemo.frame.DecoupledConstraintLayoutDemoFrame
import com.jay30.constraintlayoutdemo.frame.MainFrame

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenEnum.MAIN_FRAME.name
    ){
        composable(route = MainScreenEnum.MAIN_FRAME.name){
            MainFrame(navController)
        }

        composable(route = MainScreenEnum.CONSTRAINT_LAYOUT_DEMO_FRAME.name){
            ConstraintLayoutDemoFrame()
        }

        composable(route = MainScreenEnum.DECOUPLED_CONSTRAINT_LAYOUT_DEMO_FRAME.name){
            DecoupledConstraintLayoutDemoFrame()
        }

        composable(route = MainScreenEnum.CONSTRAINT_LAYOUT_GUIDELINES_DEMO_FRAME.name){
            ConstraintLayoutGuidelinesDemoFrame()
        }
    }
}