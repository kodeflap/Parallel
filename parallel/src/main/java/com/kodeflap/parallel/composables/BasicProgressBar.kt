package com.kodeflap.parallel.composables

import android.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp

@Composable
fun BasicProgressBar(
    size: Dp,
    foregroundIndicator: Color,
    shadowColor: Color,
    indicatorThickness: Dp,
    text: Float,
    animationDuration: Int
) {
    var text by remember {
        mutableStateOf(-1f)
    }
    val animate = animateFloatAsState(
        targetValue = text,
        animationSpec = tween(durationMillis = animationDuration)
    )
    LaunchedEffect(Unit) {
        text = text
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
                brush = Brush.radialGradient(
                    colors = listOf(
                        shadowColor,
                        Color.DKGRAY
                    ),
                    center = Offset(x = this.size.width / 2, y =)
                )
            )
            )
        }
    }
}