package com.kodeflap.parallel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kodeflap.parallel.composables.ArcProgressBar
import com.kodeflap.parallel.ui.theme.ParallelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParallelTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    CircularProgressBar(
//                        modifier = Modifier
//                            .size(250.dp)
//                            .background(Color.Black),
//                        radius = 250f ,
//                        initialValue = 10,
//                        primaryColor = PurpleGrey80,
//                        secondaryColor = Purple,
//                        onPositionChange = {
//
//                        }
//                    )
//
//                    BasicCircularProgressBar(
//                        modifier = Modifier,
//                        size = 200.dp,
//                        value = 50f,
//                        thickness = 16.dp,
//                        foregroundColor = Color.Green,
//                        backgroundColor = Color.LightGray,
//                        extraForegroundSpace = 12.dp
//                    )
//
//
//                    StackedCircularProgressBar(
//                        modifier = Modifier
//                            .size(250.dp)
//                            .background(Color.Black),
//                        radius = 250f ,
//                        initialValue = 10,
//                        primaryColor = PurpleGrey80,
//                        secondaryColor = Purple,
//                        onPositionChange = {
//
//                        }
//                    )
//
//                    GradientProgressBar(
//                        size = 250.dp,
//                        shadowColor = Ash,
//                        foregroundIndicator = Color.Green,
//                        indicatorThickness = 5.dp,
//                        text = 60f ,
//                        animationDuration = 1000
//                    )
//
//                    LinearProgressBar(
//                        backgroundColor = Color.Blue,
//                        progress = 60f,
//                        roundedCornerShape = 20.dp ,
//                        textColor = Color.White
//                    )
                    ArcProgressBar(
                        backgroundIndicatorColor = Color.Blue,
                        progressPercentage = 10f
                    )
                }
            }
        }
    }
}
