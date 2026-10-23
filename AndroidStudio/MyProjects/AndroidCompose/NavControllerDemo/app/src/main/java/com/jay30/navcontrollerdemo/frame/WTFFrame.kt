package com.jay30.navcontrollerdemo.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun WTFFrame(
    navController: NavHostController,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ){
        item{
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ){
                Text("invoke navController.popBackStack()")
            }
        }
    }
}