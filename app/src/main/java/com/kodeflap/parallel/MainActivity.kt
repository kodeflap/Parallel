package com.kodeflap.parallel

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeflap.parallel.composables.CircularProgressBar
import com.kodeflap.parallel.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParallelTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CircularProgressBar(
                        modifier = Modifier
                            .size(250.dp)
                            .background(Color.Black),
                        radius = 250f ,
                        initialValue = 10,
                        primaryColor = PurpleGrey80,
                        secondaryColor = Purple,
                        onPositionChange = {

                        }
                    )
                }
            }
        }
    }
}
