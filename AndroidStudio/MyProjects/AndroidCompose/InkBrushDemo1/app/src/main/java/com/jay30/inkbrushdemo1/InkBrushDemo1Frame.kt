package com.jay30.inkbrushdemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.stringResource
import androidx.ink.brush.Brush
import androidx.ink.brush.StockBrushes

@Composable
fun InkBrushDemo1Frame(){
    val redBrush = Brush.createWithColorIntArgb(
        family = StockBrushes.pressurePenLatest,
        colorIntArgb = Color.Red.toArgb(),
        size = 5F,
        epsilon = 0.1F
    )

    val blueBrush = redBrush.copyWithColorIntArgb(colorIntArgb = Color.Blue.toArgb())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        item{
            Text(
                text = "${stringResource(R.string.red)} ${stringResource(R.string.message)}",
                color = Color(redBrush.colorIntArgb)
            )
        }
        item{
            Text(
                text = "${stringResource(R.string.blue)} ${stringResource(R.string.message)}",
                color = Color(blueBrush.colorIntArgb)
            )
        }
    }
}