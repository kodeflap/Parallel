package com.kodeflap.parallel.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Linear progress bar
 *
 * @param backgroundColor
 * @param progress
 * @param roundedCornerShape
 * @param textColor
 */

@Composable
fun LinearProgressBar(
    backgroundColor: Color,
    progress: Float,
    roundedCornerShape: Dp,
    textColor: Color

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(roundedCornerShape))
                .height(30.dp)
                .background(backgroundColor)
                .width(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color.Green,
                                Color.Blue
                            )
                        )
                    )
                    .width(300.dp * progress / 100)
            )
            Text(
                text = "$progress %",
                color = textColor
            )
        }
    }
}