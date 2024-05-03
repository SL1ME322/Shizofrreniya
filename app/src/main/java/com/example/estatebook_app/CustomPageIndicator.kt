package com.example.estatebook_app



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomPageIndicatorMain(
    pagerState: PagerState,
    count: Int,
    circleSpacing: Float,
    activeLineWidth: Float,
    dotWidth: Float,
    activeDotWidth: Float,
    dotHeight: Float,
    radius: CornerRadius,
    modifier: Modifier

) {
    Canvas(modifier = modifier) {
        val totalWidth = (count * (dotWidth + circleSpacing) - circleSpacing)
        val startX = (size.width - totalWidth) / 2.2f
        var x = startX
        val y = size.height - dotHeight * 10
        repeat(count) { i ->
            val posOffset = pagerState.pageOffset
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()
            val factor = (dotOffset * (activeDotWidth - dotWidth))

            val color = if (i == current) {
                Color(255, 255, 255, 92)
            } else {
                Color(255, 255, 255, 92)
            }

            val calculatedWidth = when {
                i == current -> activeDotWidth - factor
                i - 1 == current || (i == 0 && posOffset > count - 1) -> dotWidth + factor
                else -> dotWidth
            }


            drawIndicators2(x, y, calculatedWidth, dotHeight, radius, color)
            x += calculatedWidth + circleSpacing;
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
fun DrawScope.drawIndicators2(
    x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, color: Color
) {
    val rect = RoundRect(x, y - height / 2, x + width, y + height / 2, radius)
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = color)
}

@Composable
fun FilterDrawer() {
    //ModelNavigationDrawer(){

    //}
}