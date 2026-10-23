package com.ron.lifecycledemo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ron.lifecycledemo.ui.theme.LifecycleDemoTheme

@Composable
fun Screen02(
    navController: NavHostController,
    uid: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User name: $uid")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = stringResource(id = R.string.back_button))
        }
    }


    val tag = "tag_Screen02"
    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d(tag, "ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d(tag, "ON_START")
                Lifecycle.Event.ON_RESUME -> Log.d(tag, "ON_RESUME")
                Lifecycle.Event.ON_PAUSE -> Log.d(tag, "ON_PAUSE")
                Lifecycle.Event.ON_STOP -> Log.d(tag, "ON_STOP")
                Lifecycle.Event.ON_DESTROY -> Log.d(tag, "ON_DESTROY")
                Lifecycle.Event.ON_ANY -> Log.d(tag, "ON_ANY")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            Log.d(tag, "onDispose")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen02Preview() {
    LifecycleDemoTheme {
        Screen02(rememberNavController(), uid = "test")
    }
}