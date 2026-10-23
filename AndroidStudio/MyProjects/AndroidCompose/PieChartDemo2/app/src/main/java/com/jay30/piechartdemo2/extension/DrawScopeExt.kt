package com.jay30.piechartdemo2.extension

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.jay30.piechartdemo2.ConvexStyle

fun DrawScope.drawConvexArc(
    color: Color,
    startAngle: Float,
    sweepAngle: Float,
    useCenter: Boolean,
    style: ConvexStyle,
) = drawIntoCanvas { canvas ->
    val rect = this.size.toRect() // The bounds of the canvas

    // Define paint object for drawing
    val paint = Paint()
    paint.color = color

    // Draw the main arc on the canvas
    canvas.drawArc(rect, startAngle, sweepAngle, useCenter, paint)

    // Function to draw shadow and glare arcs
    fun drawShadowArc(offsetX: Float, offsetY: Float, shadowColor: Color) {
        val shadowPaint = Paint() // Paint object for drawing shadows

        shadowPaint.color = shadowColor // Set shadow color

        // Save the current canvas layer
        canvas.saveLayer(rect, shadowPaint)

        // Draw the shadow arc
        canvas.drawArc(rect, startAngle, sweepAngle, useCenter, shadowPaint)

        // Apply blending mode and blur effect for the shadow
        shadowPaint.asFrameworkPaint().apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
            maskFilter = BlurMaskFilter(style.blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }

        shadowPaint.color = Color.Black // Set the color for clipping

        // Translate canvas and draw the clipping arc
        canvas.translate(offsetX, offsetY)
        canvas.drawArc(rect, startAngle, sweepAngle, useCenter, shadowPaint)

        // Restore canvas to its original state
        canvas.restore()
    }

    // Calculate offset in pixels
    val offsetPx = style.offset.toPx()

    // Draw shadow arc with negative offset
    drawShadowArc(-offsetPx, -offsetPx, style.shadowColor)

    // Draw glare arc with positive offset
    drawShadowArc(offsetPx, offsetPx, style.glareColor)
}