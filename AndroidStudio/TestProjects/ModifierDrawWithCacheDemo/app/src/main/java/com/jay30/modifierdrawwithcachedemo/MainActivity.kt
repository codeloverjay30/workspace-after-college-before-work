package com.jay30.modifierdrawwithcachedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jay30.modifierdrawwithcachedemo.ui.theme.ModifierDrawWithCacheDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifierDrawWithCacheDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(){
    Box(
        Modifier.drawWithCache {
            val gradient =
                Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Blue),
                    start = Offset.Zero,
                    end = Offset(size.width, size.height)
                )
            onDrawBehind { drawRect(gradient) }
        }
    )
}