package com.kodeflap.parallel.composables


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Gradient progress bar
 *
 * @param size
 * @param shadowColor
 * @param foregroundIndicator
 * @param indicatorThickness
 * @param text
 * @param animationDuration
 */
@Composable
fun GradientProgressBar(
    size: Dp,
    shadowColor: Color,
    foregroundIndicator: Color,
    indicatorThickness: Dp,
    text: Float,
    animationDuration: Int
) {
    var num by remember {
        mutableStateOf(-1f)
    }
    val animate = animateFloatAsState(
        targetValue = num, animationSpec = tween(durationMillis = animationDuration)
    )
    LaunchedEffect(Unit) {
        num = text
    }
    Box(
        modifier = Modifier
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(size)
        ) {
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Blue, shadowColor
                    ) //center = Offset(x = this.size.width / 2, y = this.size.height / 2)
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(Gray, Black)
                ),
                radius = (size / 2 - indicatorThickness).toPx(),
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )
            val sweepAngle = (animate.value) * 360 / 100

            drawArc(
                brush = Brush.linearGradient(
                    listOf(Transparent, foregroundIndicator)
                ),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = indicatorThickness.toPx(), cap = StrokeCap.Round
                ),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(), y = (indicatorThickness / 2).toPx()
                )
            )
        }
        DisplayText(animate = animate)
    }
    Spacer(modifier = Modifier.height(32.dp))
    ButtonProgress {
        num = (0 until 100).random().toFloat()
    }
}

@Composable
fun ButtonProgress(onClickButton: () -> Unit) {
    Button(
        onClick = {
            onClickButton()
        }, colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = "Progress")
    }
}

@Composable
fun DisplayText(animate: State<Float>) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = (animate.value).toInt().toString())
    }

}
