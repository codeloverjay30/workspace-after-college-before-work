package com.jay30.composableuicomponentsdemo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ComposableUIComponentsDemoFrame(){
    val minHeight by remember { mutableIntStateOf(2) }
    val maxWidth by remember { mutableIntStateOf(2) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ){
        item{
            BoxWithConstraints {
                Text("My minHeight is $minHeight while my maxWidth is $maxWidth")
            }

        }
    }
}