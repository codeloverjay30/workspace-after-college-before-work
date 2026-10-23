package com.jay30.brushdemo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun BrushDemoFrame(){
    LazyColumn(
        modifier = Modifier
            .size(400.dp,1500.dp)
            .border(width = 3.dp,color = Color.Blue)
    ) {
        val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
        val gradientColors = listOf(Color.Cyan, Color.Blue,Color.Red)
        item {
            // example of argument  Modifier.graphicsLayer and  Modifier.drawWithCache in Text,
            // thanks to author of [`Brushing up on Compose Text coloring`](https://medium.com/androiddevelopers/brushing-up-on-compose-text-coloring-84d7d70dd8fa)
            Text(
                text = "Text in Compose ❤️",
                modifier = Modifier.graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                        }
                    }
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                buildAnnotatedString {
                    append("Do not allow people to dim your shine\n")
                    withStyle(
                        SpanStyle(
                            brush = brush
                        )
                    ) {
                        append("because they are blinded.")
                    }
                    append("\nTell them to put some sunglasses on.")
                }
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = "A text that is linear-gradiently linearly with Color.Green, Color.Blue, Color.Red respectively",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = "A text with solid color -- Color.Cyan",
                style = TextStyle(
                    brush = SolidColor(Color.Cyan)
                )
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = "A text whose color is repeated -- Color.Blue, Color.Gray, Color.Red respectively (with TileMode.Repeated).",
                style = TextStyle(
                    brush = ScaledThirdBrush(Brush.linearGradient(
                        colors = listOf(Color.Blue,Color.Gray,Color.Red),
                        tileMode = TileMode.Repeated
                    ) as ShaderBrush)
                )
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = "A text whose color is repeated -- Color.Blue, Color.Gray, Color.Red respectively (with TileMode.Mirror).",
                style = TextStyle(
                    brush = ScaledThirdBrush(Brush.linearGradient(
                        colors = listOf(Color.Blue,Color.Gray,Color.Red),
                        tileMode = TileMode.Mirror
                    ) as ShaderBrush)
                )
            )

            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = "A text whose color is repeated -- Color.Blue, Color.Gray, Color.Red respectively (with TileMode.Clamp).",
                style = TextStyle(
                    brush = ScaledThirdBrush(Brush.linearGradient(
                        colors = listOf(Color.Blue,Color.Gray,Color.Red),
                        tileMode = TileMode.Clamp
                    ) as ShaderBrush)
                )
            )

            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = "A text whose color is repeated -- Color.Blue, Color.Gray, Color.Red respectively (with TileMode.Decal).",
                style = TextStyle(
                    brush = ScaledThirdBrush(Brush.linearGradient(
                        colors = listOf(Color.Blue,Color.Gray,Color.Red),
                        tileMode = TileMode.Decal
                    ) as ShaderBrush)
                )
            )
        }
    }
}