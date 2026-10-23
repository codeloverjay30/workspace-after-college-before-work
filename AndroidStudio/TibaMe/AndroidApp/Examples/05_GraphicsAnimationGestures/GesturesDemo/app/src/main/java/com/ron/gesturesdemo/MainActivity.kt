package com.ron.gesturesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.gesturesdemo.ui.theme.GesturesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GesturesDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Main(modifier: Modifier = Modifier) {
    var dragAmount by remember {
        mutableStateOf(Offset.Zero)
    }
    var magnificationAmount by remember {
        mutableStateOf(1f)
    }
    var rotationAmount by remember {
        mutableStateOf(0f)
    }
    Column(modifier = modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
//        Text(
//            text = "Tap Gesture",
//            modifier = Modifier.padding(all = 16.dp).clickable {
//                println("Tap Gesture Detected")
//            }
//        )
//        Text(
//            text = "Double Tap",
//            modifier = Modifier.padding(all = 16.dp).combinedClickable(
//                enabled = true,
//                onDoubleClick = {
//                    println("Double Tap Detected")
//                },
//                onClick = {}
//            )
//        )
//        Text(
//            text = "Long Press",
//            modifier = Modifier.padding(all = 16.dp).combinedClickable(
//                enabled = true,
//                onLongClick = {
//                    println("Long Press Detected")
//                },
//                onClick = {}
//            )
//        )
        Text(
            text = "Drag Gesture",
            modifier = Modifier
                .padding(all = 16.dp)
                .graphicsLayer {
                    translationX = dragAmount.x
                    translationY = dragAmount.y
                }
                .transformable(
                    state = rememberTransformableState {
                            magnificationAmountChange, dragAmountChange, rotationAmountChange ->
                        magnificationAmount = magnificationAmountChange
                        rotationAmount = rotationAmountChange
                        dragAmount += dragAmountChange
                    }
                )
        )
//        Text(
//            text = "Magnification Gesture",
//            modifier = Modifier
//                .padding(all = 16.dp)
//                .graphicsLayer {
//                    scaleX = magnificationAmount
//                    scaleY = magnificationAmount
//                }
//                .transformable(
//                    state = rememberTransformableState {
//                            magnificationAmountChange, dragAmountChange, rotationAmountChange ->
//                        magnificationAmount *= magnificationAmountChange
//                        rotationAmount = rotationAmountChange
//                        dragAmount = dragAmountChange
//                    }
//                )
//        )
//        Text(
//            text = "Rotation Gesture",
//            modifier = Modifier
//                .padding(all = 16.dp)
//                .graphicsLayer {
//                    rotationZ = rotationAmount
//                }
//                .transformable(
//                    state = rememberTransformableState {
//                            magnificationAmountChange, dragAmountChange, rotationAmountChange ->
//                        magnificationAmount = magnificationAmountChange
//                        rotationAmount += rotationAmountChange
//                        dragAmount = dragAmountChange
//                    }
//                )
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GesturesDemoTheme {
        Main()
    }
}