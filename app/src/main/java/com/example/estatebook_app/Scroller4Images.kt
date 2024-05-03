package com.example.estatebook_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scroller4Images(pagerState: PagerState, modifier: Modifier) {
    HorizontalPager(state = pagerState, modifier = Modifier) { page ->
        Row() {
            when (page) {
                0 -> Box(modifier = Modifier.height(265.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.estate1),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "ccxc",
                    )
                }
                1 -> Box(modifier = Modifier.height(265.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.estate2),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "ccxc", modifier = Modifier.fillMaxSize()
                    )
                }
                2 -> Box(modifier = Modifier.height(265.dp)) {
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

