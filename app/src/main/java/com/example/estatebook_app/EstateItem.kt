package com.example.estatebook_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estatebook_app.data.remote.EstateMain


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EstateItem(
    estate: EstateMain,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { 3 }
    Box(
        Modifier
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(color = Color.Blue), contentAlignment = Alignment.Center
    ) {

        Scroller4Images(pagerState = pagerState)



        CustomPageIndicatorMain(
            pagerState,
            3,
            44f,
            1200f,
            35f,
            126f,
            35f,
            CornerRadius(120f),
            modifier = Modifier

                .offset(-15.dp, 56.dp)
                .align(Alignment.BottomCenter),
        )
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Color(0, 0, 0, 64)), horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.fillMaxWidth(0.03f))
            Column() {
                Text(  text = estate.Ad_Name, fontSize = 32.sp, color = Color.White )
                Text(text = estate.Area.toString(), fontSize = 20.sp, color = Color.White)

            }
            Spacer(modifier = Modifier.fillMaxWidth(0.22f))
            Column(verticalArrangement = Arrangement.Center  ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(23.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "fkdkdk"
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = estate.Rating.toString(), color = Color.White, fontSize = 20.sp)
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .offset(-3.dp, 0.dp)   ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(23.dp) ) {
                            Image(
                                painter = painterResource(id = R.drawable.gps),
                                contentDescription = "sd"
                            )
                        }

                        Text(
                            text = "8 км от вас",
                            color = Color.White,
                            fontSize = 20.sp,modifier = Modifier.fillMaxWidth(),
                        )
                    }

                }


            }
        }




    }
}

//@Preview
//@Composable
//fun EstateItemPreview() {
//    EstateBook_AppTheme() {
//        EstateItem(
//            estate = EstateMain(
//                id = 1,
//                Ad_Name = "Дворец",
//                Location = "метро южная",
//                Price = 212,
//                Area = 122,
//                Estate_Images_ID = 1
//            )
//        )
//    }
//}