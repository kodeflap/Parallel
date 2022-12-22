package com.kodeflap.parallel.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Basic circular progress bar
 *
 * @param modifier
 * @param size
 * @param value
 * @param thickness
 * @param animationDuration
 * @param animationDelay
 * @param foregroundColor
 * @param backgroundColor
 * @param extraForegroundSpace
 */

@Composable
fun BasicCircularProgressBar(
    modifier: Modifier = Modifier,
    size: Dp,
    value: Float,
    thickness: Dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    foregroundColor: Color,
    backgroundColor: Color,
    extraForegroundSpace: Dp
) {
    var progressValue by remember {
        mutableStateOf(-1f)
    }
    val animate = animateFloatAsState(
        targetValue = progressValue,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(Unit) {
        progressValue = value
    }

    Box(
        modifier = Modifier
            .size(size = size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(size = size)
        ) {
            drawCircle(
                color = backgroundColor,
                radius = size.toPx() / 2,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Round)
            )
            val angle = (animate.value / 100) * 360

            drawArc(
                color = foregroundColor,
                startAngle = -90f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke((thickness + extraForegroundSpace).toPx(), cap = StrokeCap.Butt)
            )
        }

        Text(
            text = (animate.value).toInt().toString(),
        )
    }

    Spacer(modifier = Modifier.height(32.dp))
    ProgressBar(backgroundColor = backgroundColor) {
        progressValue = (1..100).random().toFloat()
    }
}

@Composable
fun ProgressBar(
    backgroundColor: Color,
    onClickButton: () -> Unit
) {
    Button(
        onClick = { onClickButton() },
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(
            text = "basic Progress bar",
            color = Color.White
        )
    }
}