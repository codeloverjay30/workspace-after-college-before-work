package com.jay30.currentactivitywindowdemo

import android.app.Activity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CurrentActivityWindowDemoFrame(
    activity: Activity,
){
    // Get the current display
    val activityDisplay = activity.display

    // Get the current activity window metrics
    val windowMetrics = activity.windowManager.currentWindowMetrics

    // Get the maximum window metrics for the current system configuration
    val maximumWindowMetrics = activity.windowManager.maximumWindowMetrics

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ){
        item{
            Text("activity.display:${activityDisplay}")

            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))

            Text("activity.windowManager.currentWindowMetrics:${windowMetrics}")

            Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))

            Text("activity.windowManager.maximumWindowMetrics:${maximumWindowMetrics}")
        }
    }

}