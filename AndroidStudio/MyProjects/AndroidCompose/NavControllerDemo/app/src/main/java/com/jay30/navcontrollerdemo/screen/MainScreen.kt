package com.jay30.navcontrollerdemo.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay30.navcontrollerdemo.frame.WTFFrame
import com.jay30.navcontrollerdemo.frame.WhatFrame

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenEnum.WTF_FRAME.name,
    ){
        composable(route = MainScreenEnum.WTF_FRAME.name) {
            WTFFrame(navController = navController)
        }

        composable(route = MainScreenEnum.WHAT_FRAME.name) {
            WhatFrame(navController = navController)
        }
    }
}