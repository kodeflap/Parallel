package com.kodeflap.parallel.composables

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    radius: Float,
    initialValue: Int,
    minValue: Int = 0,
    maxValue: Int = 100,
    primaryColor: Color,
    secondaryColor: Color,
    onPositionChange: (Int) -> Unit
) {
    var center by remember {
        mutableStateOf(Offset.Zero)
    }
    val position by remember {
        mutableStateOf(initialValue)
    }

    Box(modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val thickness = width / 25f
            center = Offset(x = width / 2f, y = height / 2f)

            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        primaryColor.copy(0.4f),
                        secondaryColor.copy(0.2f)
                    )
                ),
                radius = radius,
                center = center
            )

            drawCircle(
                style = Stroke(width = thickness),
                color = secondaryColor,
                radius = radius,
                center = center
            )

            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle = (360f / maxValue) * position.toFloat(),
                style = Stroke(
                    width = thickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = radius * 2f,
                    height = radius * 2f
                ),
                topLeft = Offset(
                    (width - radius * 2f) / 2f,
                    (height - radius * 2f) / 2f
                )
            )

            val outerRadius = radius + thickness / 2f
            val gap = 15f
            for (i in 0..(maxValue - minValue)) {
                val color =
                    if (i < position - minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
                val angleInDegree = i * 360f / (maxValue - minValue).toFloat()
                val angleInRad = angleInDegree * PI / 180f + PI / 2f
                val yGap = cos(angleInDegree * PI / 180f) * gap
                val xGap = -sin(angleInDegree * PI / 180f) * gap

                val start = Offset(
                    x = (outerRadius * cos(angleInRad) + center.x + xGap).toFloat(),
                    y = (outerRadius * sin(angleInRad) + center.y + yGap).toFloat()
                )
                val end = Offset(
                    x = (outerRadius * cos(angleInRad) + center.x + xGap).toFloat(),
                    y = (outerRadius * sin(angleInRad) + thickness + center.y + yGap).toFloat()
                )
                rotate(
                    angleInDegree,
                    pivot = start
                ) {
                    drawLine(
                        color = color,
                        start = start,
                        end = end,
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
        drawContext.canvas.nativeCanvas.apply {
            drawIntoCanvas {
                drawText(
                    "$position %",
                    center.x,
                    center.y + 45.dp.toPx() / 3f,
                    Paint().apply {
                        textSize = 38.sp.toPx()
                        textAlign = Paint.Align.CENTER
                        color = primaryColor.toArgb()
                        isFakeBoldText = true
                    }
                )
            }
          }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    CircularProgressBar(
        modifier = Modifier
            .size(250.dp)
            .background(Color.DarkGray),
        radius = 230f ,
        initialValue = 50 ,
        primaryColor = Color.White,
        secondaryColor = Color.Blue,
        onPositionChange = {

        }
    )
}