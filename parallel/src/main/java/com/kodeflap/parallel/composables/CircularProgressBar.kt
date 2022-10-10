package com.kodeflap.parallel.composables

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var position by remember {
        mutableStateOf(initialValue)
    }

    Box(modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val stroke = width / 25f
            center = Offset(x = width / 2f, y = height / 2f)

            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        primaryColor.copy(0.4f),
                        secondaryColor.copy(0.2f)
                    )
                ),
                radius = circleRadius,
                center = circleCenter
            )

            drawCircle(
                style = Stroke(width = stroke),
                color = secondaryColor,
                radius = radius,
                center = center
            )

            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle = (360f / maxValue) * position.toFloat(),
                style = Stroke(
                    width = radius * 2f,
                    height = radius * 2f
                ),
                topLeft = Offset(
                    (width - radius * 2f) / 2f,
                    (height - radius * 2f) / 2f
                )
            )
        }

        val outerRadius = radius + stroke / 2f
        val gap = 15f
        for (i in 0..(maxValue - minValue)) {
            val color =
                if (i < position - minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
            val angleInDegree = i * 360 / (maxValue - minValue).toFloat()
            val angleInRad = angleInDegree * PI / 180f + PI / 2f
            val yGap = cos(angleInDegree * PI / 180f) * gap
            val xGap = -sin(angleInDegree * PI / 180f) * gap

            val start = Offset(
                x = (outerRadius * cos(angleInRad) + center.x + xGap).toFloat(),
                y = (outerRadius * sin(angleInRad) + center.y + yGap).toFloat()
            )
            val end = Offset(
                x = (outerRadius * cos(angleInRad) + center.x + xGap).toFloat(),
                y = (outerRadius * sin(angleInRad) + stroke + center.y + yGap).toFloat()
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
                        color = white.toArgb()
                        isFakeBoldeText = true
                    }
                )
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
            .background(darkGray),
        radius = 230f ,
        initialValue = 50 ,
        primaryColor = blue,
        secondaryColor = lightBlue,
        onPositionChange = {

        }
    )
}