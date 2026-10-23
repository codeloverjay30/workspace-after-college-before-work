package com.jay30.windowsizeclassdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

@Composable
fun WindowSizeClassDemoFrame(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
){
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ){
        item{
            Text("windowSizeClass.windowWidthSizeClass:${windowSizeClass.windowWidthSizeClass}")
            Text("windowSizeClass.windowHeightSizeClass:${windowSizeClass.windowHeightSizeClass}")
        }
    }
}