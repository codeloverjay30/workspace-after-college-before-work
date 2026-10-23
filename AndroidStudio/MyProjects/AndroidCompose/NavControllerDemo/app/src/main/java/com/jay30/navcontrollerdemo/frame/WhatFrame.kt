package com.jay30.navcontrollerdemo.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jay30.navcontrollerdemo.screen.MainScreenEnum2

@Composable
fun WhatFrame(
    navController:NavHostController,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ){
        item{
            Button(
                onClick = {
                    // navController.popBackStack(destinationId = MainScreenEnum2.WTF_FRAME.title, inclusive = false)
                }
            ){
                Text("invoke navController.popBackStack(destinationId = MainScreenEnum2.WTF_FRAME.title, inclusive = false)")
            }
        }
        item{
            Button(
                onClick = {
                    // navController.popBackStack(destinationId = MainScreenEnum2.WTF_FRAME.title, inclusive = true)
                }
            ){
                Text("invoke navController.popBackStack(destinationId = MainScreenEnum2.WTF_FRAME.title, inclusive = true)")
            }
        }
    }
}