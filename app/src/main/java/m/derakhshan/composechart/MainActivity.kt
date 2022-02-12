package m.derakhshan.composechart

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m.derakhshan.composechart.ui.theme.ComposeChartTheme

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    var showChart by remember {
                        mutableStateOf(true)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                600.dp
                            )
                    ) {
                        Chart(
                            data = mapOf(
                                Pair("Jan", 10f),
                                Pair("Feb", 5f),
                                Pair("Mar", 5f),
                                Pair("Apr", 5f),
                                Pair("May", 5f),
                                Pair("Jun", 15f),
                                Pair("July", 15f),
                            ), height = 250.dp,
                            isExpanded = showChart,
                            bottomEndRadius = 30.dp,
                            bottomStartRadius = 30.dp
                        ) {
                            showChart = !showChart
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeChartTheme {
        Greeting("Android")
    }
}