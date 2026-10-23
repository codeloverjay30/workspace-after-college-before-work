package com.jay30.piechartdemo2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PieChartPanel(
    modifier: Modifier,
    platesColor: Color = Color(0xFFD5F3FF),
    platesGap: Dp = 32.dp,
    style: ConvexStyle = ConvexStyle(
        blur = 12.dp,
        offset = 8.dp,
        glareColor = Color.White.copy(alpha = 0.32f),
        shadowColor = Color.Black.copy(alpha = 0.32f)
    ),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        // Outer Box representing the entire panel
        modifier = modifier
            .aspectRatio(1f) // Ensure aspect ratio of 1:1
            // Apply inner shadows to create depth effect
            .innerShadow(CircleShape, style.glareColor, style.blur, -style.offset, -style.offset)
            .innerShadow(CircleShape, style.shadowColor, style.blur, style.offset, style.offset)
            // Apply drop shadows to create elevation effect
            .dropShadow(CircleShape, style.glareColor, style.blur, -style.offset, -style.offset)
            .dropShadow(CircleShape, style.shadowColor, style.blur, style.offset, style.offset)
            // Draw the backgroud
            .background(platesColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            // Inner Box to contain the actual content
            modifier = Modifier
                .matchParentSize() // Occupy entire parent-size
                .padding(platesGap) // Add gap between plates
                // Apply drop shadows to create elevation effect
                .dropShadow(CircleShape, style.glareColor, style.blur, -style.offset, -style.offset)
                .dropShadow(CircleShape, style.shadowColor, style.blur, style.offset, style.offset)
                // Draw the backgroud
                .background(platesColor, CircleShape),
            contentAlignment = Alignment.Center,
            content = content // Paste the content
        )
    }
}