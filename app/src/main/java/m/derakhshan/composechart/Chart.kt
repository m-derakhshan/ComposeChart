package m.derakhshan.composechart


import android.graphics.Paint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun Chart(
    data: Map<Any, Float>,
    barCornersRadius: Float = 25f,
    barColor: Color = Color.Blue,
    barWidth: Float = 50f,
    height: Dp,
    labelOffset: Float = 60f,
    labelColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    topStartRadius: Dp = 0.dp,
    topEndRadius: Dp = 0.dp,
    bottomStartRadius: Dp = 0.dp,
    bottomEndRadius: Dp = 0.dp,
    isExpanded: Boolean = true,
    closeIcon: ImageVector = Icons.Default.KeyboardArrowUp,
    onCloseListener: () -> Unit
) {

    val shape = RoundedCornerShape(
        topStart = topStartRadius,
        topEnd = topEndRadius,
        bottomEnd = bottomEndRadius,
        bottomStart = bottomStartRadius
    )

    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) height else 50.dp,
        animationSpec = tween(
            1000,
            easing = LinearOutSlowInEasing
        )
    )
    val rotate by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 180f,
        animationSpec = tween(
            700,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .shadow(2.dp, shape = shape)
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .animateContentSize()
    ) {

        IconButton(onClick = onCloseListener) {
            Icon(
                imageVector = closeIcon,
                contentDescription = "Close chart",
                modifier = Modifier.rotate(rotate)
            )
        }


        Canvas(modifier = Modifier
            .fillMaxSize()
            .alpha(if (cardHeight < height) (cardHeight - 90.dp) / height else 1f)
            .padding(
                top = 50.dp,
                bottom = 20.dp,
                start = 30.dp,
                end = 30.dp
            ),
            onDraw = {
                val spaceBetweenBars =
                    (size.width - (data.size * barWidth)) / (data.size - 1)
                val maxBarHeight = data.values.maxOf { it }
                val barScale = size.height / maxBarHeight
                val paint = Paint().apply {
                    this.color = labelColor.toArgb()
                    textAlign = Paint.Align.CENTER
                    textSize = 40f
                }
                var spaceStep = 0f

                for (item in data) {

                    val topLeft = Offset(
                        x = spaceStep,
                        y = size.height - item.value * barScale
                    )

                    drawRoundRect(
                        color = barColor,
                        topLeft = topLeft,
                        size = Size(
                            width = barWidth,
                            height = size.height - topLeft.y - labelOffset
                        ),
                        cornerRadius = CornerRadius(barCornersRadius, barCornersRadius)
                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        item.key.toString(),
                        spaceStep + barWidth / 2,
                        size.height,
                        paint
                    )
                    spaceStep += spaceBetweenBars + barWidth
                }
            })


    }
}