package m.derakhshan.composechart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
                                Pair("Jan", 6f),
                                Pair("Feb", 0.25f),
                                Pair("Mar", 9f),
                                Pair("Apr", 7f),
                                Pair("May", 8f),
                                Pair("Jun", 9f),
                                Pair("Jul", 3f),
                                Pair("Aug", 11f),
                                Pair("Sep", 15f),
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