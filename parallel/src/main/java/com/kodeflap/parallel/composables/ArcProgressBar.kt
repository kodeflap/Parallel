package com.kodeflap.parallel.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.lang.Math.cos
import kotlin.math.sin

/**
 * Arc progress bar
 *
 * @param ProgressSize
 * @param backgroundIndicatorColor
 * @param progressPercentage
 */

@Composable
fun ArcProgressBar(
    ProgressSize: Dp = 100.dp,
    backgroundIndicatorColor: Color,
    progressPercentage: Float
) {
    Canvas(
        modifier = Modifier
            .size(ProgressSize)
            .padding(10.dp)
    ) {
        drawArc(
            color = backgroundIndicatorColor,
            140f,
            260f,
            false,
            style = Stroke(10.dp.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        /**
         * Foreground
         */
        drawArc(
            color = backgroundIndicatorColor,
            140f,
            260f,
            false,
            style = Stroke(10.dp.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        val angleInDegrees = (progressPercentage * 260.0) + 50.0
        val radius = (size.height / 2)
        val x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        val y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)

        drawCircle(
            color = Color.White,
            radius = 5f,
            center = Offset(x, y)
        )

    }
}

