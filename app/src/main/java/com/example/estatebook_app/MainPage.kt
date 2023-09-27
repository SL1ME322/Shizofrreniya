package com.example.estatebook_app

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.estatebook_app.data.remote.EstateMain


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPage(  estates : LazyPagingItems<EstateMain>) {
    val pagerState = rememberPagerState (3)
    val context = LocalContext.current
    LaunchedEffect(key1 = estates.loadState) { //безопасный запуск корутины, если key изменяется - то launchedeffect перезапускается
        if (estates.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Ошибка: " + (estates.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = (listOf(
                    Color(84, 230, 116, 255),
                    Color(168, 238, 183, 255),

                    ))
            )
        )
    ) {

        Column() {
            Spacer(modifier = Modifier.fillMaxHeight(0.01f))
            Row(horizontalArrangement = Arrangement.Center,  ) {
                val text = remember {
                    mutableStateOf("")
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.01f))
                IconButton(modifier = Modifier
                    .size(70.dp, 70.dp)
                    .offset(0.dp, -5.dp), onClick = { /*TODO*/ }, content = {
                    Icon(
                        modifier = Modifier.size(43.dp, 43.dp),
                        painter = painterResource(id = R.drawable.baseline_format_align_justify_24),
                        contentDescription = "flfl"
                    )
                })
                Spacer(modifier = Modifier.fillMaxWidth(0.08f))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    value = text.value,
                    shape = RoundedCornerShape(29.dp),
                    onValueChange = { newText -> text.value = newText },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(35.dp, 35.dp),
                            imageVector = Icons.Filled.Search,
                            contentDescription = "dld"
                        )
                    },
                    singleLine = true,


                    )
                Spacer(modifier = Modifier.fillMaxWidth(0.08f))

            }
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Image(
                    painter = painterResource(id = R.drawable.baseline_fmd_good_24),
                    contentDescription = "dlsdkks",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 10.dp)
                )

                Text(text = "г.Москва", fontSize = 28.sp, fontWeight = FontWeight(600))
            }
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Text(
                modifier = Modifier.padding(start = 32.dp, bottom = 10.dp),
                text = "53 объявления найдено",
                fontSize = 24.sp,
                fontWeight = FontWeight(400)
            )

            Row() {
                Column(modifier = Modifier.fillMaxWidth(0.55f)) {
                    Row() {
                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                        Box(
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(30.dp),
                                color = Color(219, 204, 125, 255)
                            )
                        ) {
                            Row() {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 11.dp)
                                ) {

                                    Image(
                                        modifier = Modifier
                                            .size(12.dp),

                                        painter = painterResource(id = R.drawable.cross),
                                        contentDescription = "dkskdsk"
                                    )
                                    Spacer(modifier = Modifier.fillMaxWidth(0.02f))
                                    Text(text = "Тэг 1", fontSize = 20.sp)
                                }


                            }

                        }
                        Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                        Box(
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(30.dp),
                                color = Color(219, 204, 125, 255)
                            )
                        ) {
                            Row() {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 11.dp)
                                ) {

                                    Image(
                                        modifier = Modifier
                                            .size(12.dp),

                                        painter = painterResource(id = R.drawable.cross),
                                        contentDescription = "dkskdsk"
                                    )
                                    Spacer(modifier = Modifier.fillMaxWidth(0.02f))
                                    Text(text = "Тэг 1", fontSize = 20.sp)
                                }


                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(38.dp),
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = "ldld"
                        )
                        Text(text = "Фильтры", fontSize = 18.sp)
                    }

                }
            }
            Box(modifier = Modifier.fillMaxSize()){
                if (estates.loadState.refresh is LoadState.Loading){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
               //items(estates) {  estate -> if (estate != null){
               //    EstateItem(estate = estate, modifier = Modifier.fillMaxSize())
               //}
                items(estates) {//перебор листа
                        estate ->
                    if (estate != null) {
                        EstateItem(estate = estate, modifier = Modifier.fillMaxWidth())
                    }
                }
                item{
                    if(estates.loadState.append is LoadState.Loading){
                        CircularProgressIndicator()
                    }
                }

                }

            }


        }
    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scroller4Images(pagerState: PagerState) {
    HorizontalPager(state = pagerState, modifier = Modifier, pageCount = 3 ) { page ->
        Row() {
            when (page) {
                0 -> Box(modifier = Modifier.height(265.dp))  {
                    Image(
                        painter = painterResource(id = R.drawable.estate1),modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "ccxc",
                    )
                }
                1 -> Box(modifier = Modifier.height(265.dp   )) {
                    Image(
                        painter = painterResource(id = R.drawable.estate2),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "ccxc", modifier = Modifier.fillMaxSize()
                    )
                }
                2 -> Box(modifier = Modifier.height(265.dp   ))  {
                    Image(
                        painter = painterResource(id = R.drawable.estate3),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "ccxc", modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomPageIndicatorMain(
    pagerState: PagerState,
    count: Int,
    circleSpacing: Float,
    activeLineWidth: Float,
    dotWidth: Float,
    activeDotWidth: Float,
    dotHeight:Float,
    radius:CornerRadius,
    modifier: Modifier

){
    Canvas(modifier = modifier ){
        val totalWidth = (count *(dotWidth + circleSpacing) - circleSpacing)
        val startX = (size.width - totalWidth) / 2.2f
        var x = startX
        val y = size.height - dotHeight * 10
        repeat(count){
            i ->
            val posOffset = pagerState.pageOffset
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()
            val factor = (dotOffset * (activeDotWidth - dotWidth))

            val color = if(i == current){
                Color(255, 255, 255, 92)
            }
            else{
                Color(255, 255, 255, 92)
            }

            val calculatedWidth = when{
                i == current -> activeDotWidth - factor
                i - 1 == current || (i == 0 && posOffset > count - 1) -> dotWidth + factor
                else -> dotWidth
            }

            drawIndicators2(x,y,calculatedWidth,dotHeight,radius,color)
            x += calculatedWidth + circleSpacing;
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
fun DrawScope.drawIndicators2(
    x:Float,  y:Float, width: Float, height: Float, radius: CornerRadius, color: Color
)
{
    val rect = RoundRect(x,y - height/2, x + width, y + height/2, radius)
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = color)
}