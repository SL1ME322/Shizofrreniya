package com.example.estatebook_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.estatebook_app.data.remote.EstateMain

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EstatePage(estate: EstateMain?, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(elevation = 4.dp) {
                Row() {
                    IconButton(
                        onClick = {  navController.popBackStack() }, modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 20.dp)
                            .fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 20.dp)
                            .fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_share_24),
                            contentDescription = "Back"
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = "Back"
                        )
                    }
                }
            }
        },
    ) {
        val pagerState = rememberPagerState { 3 }

        Column(
            Modifier
                .background(Color(110, 131, 238, 40))
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            ) {
                Scroller4Images(pagerState = pagerState, modifier = Modifier)
            }
            Text(text = estate?.Ad_Name ?: "fdfd", style = TextStyle(fontSize = 28.sp))
            Row(Modifier.fillMaxWidth()){
                Text(
                    text = (estate?.Price.toString() + " руб") ?: "fdfd",
                    style = TextStyle(fontSize = 20.sp)
                )
                    RatingBar(rating = 0.75, )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        softWrap = true,
                        text = "г. Москва",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 19.sp),
                        modifier = Modifier.padding(horizontal = 10.dp),
                    )
                    //Box(
                    //    Modifier
                    //        .size(7.dp)
                    //        .clip(shape = CircleShape)
                    //        .background(Color.Black)
                    //)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.metro),
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )

                        Text(
                            text = "Нахимовский проспект", overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
                                textAlign = TextAlign.Center
                            ),

                            )
                    }


                }


                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    Image(
                        painter = painterResource(id = R.drawable.kmfromyou),
                        contentDescription = "",
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "14 км от вас", Modifier.padding(horizontal = 10.dp),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp
                        )
                    )
                }


            }
            Row() {
                Image(
                    painter = painterResource(R.drawable.mapsicon),
                    contentDescription = "",
                    Modifier.size(25.dp)
                )
                Text(text = "Показать на карте", color = Color.Blue)
            }
            val paragraphStyle = ParagraphStyle(textIndent = TextIndent(firstLine = 24.sp))

            Text(
                text = AnnotatedString(
                    estate?.Description ?: "fdfd",
                    paragraphStyle = paragraphStyle,
                ),
                Modifier.padding(start = 14.dp, end = 14.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            )
            Box(Modifier.padding(start = 20.dp, top = 20.dp)) {
                ProfileOnEstate( estate!!.User_ID, navController,
                    modifier = Modifier.clickable {  val navigationRoute = "ProfilePage/${estate!!.User_ID}"
                        println("Navigating to: $navigationRoute")
                        navController.navigate(navigationRoute) })
            }
            var rating5 by remember { mutableStateOf(1.7f) }

            RatingBar(modifier = Modifier.fillMaxSize(), rating =5.0 )


        }
    }


}
