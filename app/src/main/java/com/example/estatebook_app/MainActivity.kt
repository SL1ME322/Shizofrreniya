    package com.example.estatebook_app
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.annotation.DrawableRes
    import androidx.compose.foundation.Canvas
    import androidx.compose.foundation.ExperimentalFoundationApi
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.pager.HorizontalPager
    import androidx.compose.foundation.pager.PagerState
    import androidx.compose.foundation.pager.rememberPagerState
    import androidx.compose.material.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Alignment.Companion.End
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.geometry.CornerRadius
    import androidx.compose.ui.geometry.RoundRect
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.Path
    import androidx.compose.ui.graphics.TileMode
    import androidx.compose.ui.graphics.drawscope.DrawScope
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.example.estatebook_app.ui.theme.EstateBook_AppTheme
    import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

    class MainActivity : ComponentActivity() {
        companion object{
            var accessToken =""
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
               EstateBook_AppTheme() {

                   val navController = rememberNavController()
                   // A surface container using the 'background' color from the theme
                   Surface(
                       modifier = Modifier.fillMaxSize(),
                       color = Color(171, 238, 203, 255)
                   )
                   {

                      //EstateItem(
                      //    estate = EstateMain(
                      //        id = 1,
                      //        Ad_Name = "Дворец",
                      //        Location = "метро южная",
                      //        Price = 212,
                      //        Area = 122,
                      //        Rating = 4.6,
                      //        Estate_Images_ID = 1
                      //    ), modifier = Modifier.fillMaxWidth()
                      //)

                       SetupNavHost()
                       val viewModel = hiltViewModel<EstateViewModel>()

                      // MainPage(viewModel)                    //RegisrationBox(navController)
                       //mainFunc(navController = navController)
                   }
               }

            }
        }
    }



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun mainFunc(navController: NavController) {

    //firstSlide()
    //secondSlide()
    val pagerState = rememberPagerState { 3 }


    CustomPageIndicator(
        pagerState,
        3,
        44f,
        1200f,
        45f,
        126f,
        45f,
        CornerRadius(120f),
        modifier = Modifier
    )
    Scroller(navController = navController, pagerState)
    //LazyRow(){
    //    items(3){
    //        page -> when (page){
    //            0 -> firstSlide()
    //            1 -> secondSlide()
    //            2 -> thirdSlider()
    //        }
    //    }
    //}

        //thirdSlider()
}

@Composable
fun firstSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    tileMode = TileMode.Clamp,
                    startY = 0f,
                    endY = 2000f,

                    colors = listOf(
                        Color(
                            red = 84,
                            green = 230,
                            blue = 116,
                            alpha = 255
                        ), // Цвет без альфа-канала (полностью непрозрачный)
                        Color(
                            red = 255,
                            green = 255,
                            blue = 255,
                            alpha = 158
                        ) // Цвет с альфа-каналом, 0.1f означает 10% непрозрачность
                    )

                )
            )
    ) {
        Box(modifier = Modifier
            .align(alignment = End)
            .padding(top = 15.dp, end = 7.dp)) {
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(90.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(text = "Skip", fontSize = 24.sp)
            }
        }
        imgAndTexts(
            0.4f,
            1f,
            drawable = R.drawable.firstintroimg,
            text1 = "Ищите недвижимость с легкостью !",
            text2 = "Мы представляем огромный каталог недвижимости всех типов. Дом мечты уже ждет вас!"
        )
    }
}

@Composable
fun secondSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    startY = 0f,
                    endY = 2000f, tileMode = TileMode.Clamp,
                    colors = listOf(
                        Color(red = 183, green = 160, blue = 248),
                        Color(red = 223, green = 214, blue = 248, alpha = 143)
                    )
                )
            )
    ) {
        Box(modifier = Modifier
            .align(alignment = End)
            .padding(top = 15.dp, end = 7.dp)) {
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(90.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(text = "Skip", fontSize = 24.sp)
            }
        }
        imgAndTexts(
            0.3901f,
            0.95f,
            drawable = R.drawable.secondimg,
            text1 = "Выставляйте свою недвижимость на продажу",
            text2 = "Находите покупателей вашей недвижимости быстро и без хлопот "
        )

    }
}

@Composable
fun thirdSlider(navController: NavController) {
    Column(
        modifier = Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    Color(
                        red = 155,
                        green = 197,
                        blue = 235
                    ), Color(red = 203, green = 227, blue = 250, alpha = 149)
                )
            )
        )
    ) {

        Box() {

            Canvas(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
                val path = Path().apply {
                    moveTo(0f, 0f) // Верхний центр
                    lineTo(size.width * 0.5f, 0f) // Правый верхний угол
                    lineTo(size.width, size.height * 0.1f) // Правый нижний угол
                    lineTo(size.width , size.height * 0.3f) // Левый нижний угол
                    lineTo(0f, size.height * 0.4f) // Левый верхний угол
                    close()

                }




                drawPath(color = Color(0xFF5D5AEE), path = path)

            }

            imgAndTexts2(
               heightImg = 0.435f,
                widthImg = 1f,
                drawable = R.drawable.handshake5,
                text1 = "Поддерживайте обратную связь с продавцом",
                text2 = "Договаоривайтесь о покупке с владельцами недвижимости уже сейчас !",
                navController = navController

            )


        }


    }
}
@Composable
fun imgAndTexts(
    heightImg: Float,
    widthImg: Float,
    @DrawableRes drawable: Int,
    text1: String,
    text2: String,

) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(heightImg)
                .fillMaxWidth(widthImg)
                .padding(bottom = 24.dp),
            painter = painterResource(id = drawable),
            contentDescription = "sacaaw", alignment = Alignment.Center
        )
        Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = text1,
            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )
        Text(
            modifier = Modifier.padding(start = 17.dp, end = 17.dp),
            lineHeight = 35.sp,
            fontSize = 23.sp, textAlign = TextAlign.Center,
            text = text2

        )
    }
}
@Composable
fun imgAndTexts2(
    heightImg: Float,
    widthImg: Float,
    @DrawableRes drawable: Int,
    text1: String,
    text2: String,
    navController: NavController
) {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            modifier = Modifier
                .fillMaxHeight(heightImg)
                .fillMaxWidth(widthImg)
                .padding(bottom = 24.dp),
            painter = painterResource(id = drawable),
            contentDescription = "sacaaw", alignment = Alignment.Center

        )
        Column(modifier = Modifier.offset(0.dp,-30.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = text1,
                textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
            Text(
                modifier = Modifier.padding(start = 17.dp, end = 17.dp),
                lineHeight = 35.sp,
                fontSize = 23.sp, textAlign = TextAlign.Center,
                text = text2

            )

        }
        Box(modifier = Modifier.fillMaxSize()) {
            TextButton( colors = ButtonDefaults.buttonColors
                (backgroundColor = Color(
                red = 32,
                green = 212,
                blue = 114,
                alpha = 255
            )
            ),
                modifier = Modifier

                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .align(Alignment.BottomCenter), onClick = {navController.navigate("Register")})
            {
                Text(text = "Начать", fontSize = 22.sp    )

            }
        }

    }
}



    

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EstateBook_AppTheme {
        //mainFunc(navController)
    }
}
 @OptIn(ExperimentalFoundationApi::class)
 fun DrawScope.drawIndicators(
    x:Float,
    y:Float,
    width:Float,
    height:Float,
    radius:CornerRadius,
    color: Color


){

    val rect = RoundRect(x, y - height/2 , x+ width, y + height/2, radius )
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = color)





}

@OptIn(ExperimentalFoundationApi::class)
val PagerState.pageOffset : Float
    get() = this.currentPage + this.currentPageOffsetFraction

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page:Int):Float{
    return (currentPage - page) +currentPageOffsetFraction
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomPageIndicator(
    pagerState: PagerState,
    count: Int,
    circleSpacing: Float,
    activeLineWidth: Float,
    dotWidth: Float,
    activeDotWidth: Float,
    dotHeight:Float,
    radius:CornerRadius,
    modifier: Modifier

) {
    Canvas(modifier = modifier) {
        val totalWidth = (count * (dotWidth + circleSpacing) - circleSpacing)
        val startX = (size.width - totalWidth) / 2.2f
        val active = false

        var x = startX
        val y = size.height - dotHeight * 10
        repeat(count) { i ->
             val posOffset = pagerState.pageOffset
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()
            val factor = (dotOffset *(activeDotWidth - dotWidth))

           // drawRoundRect(
           //     color = color, topLeft = offset,
           //     size = Size(width, dotHeight),
           //     cornerRadius = radius,
           // )

            val color = if(i == current){

                Color(84,165,55,255)
            }
            else{
                 Color.Green
            }

            val calculatedWidth = when{
                i == current -> activeDotWidth - factor
                i - 1 == current || (i==0 && posOffset > count - 1) -> dotWidth + factor
                else -> dotWidth
            }

            drawIndicators(x,y,calculatedWidth, dotHeight, radius, color)
            x += calculatedWidth + circleSpacing;
        }
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scroller(navController: NavController,
    pagerState: PagerState
){


    HorizontalPager(state = pagerState ) {
        page ->  Row() {
        when (page){
           0 -> firstSlide()
            1 ->          secondSlide()
            2 ->          thirdSlider(navController)
        }


    }
   // LazyRow(modifier = Modifier.fillMaxWidth()){
   //     items(3){ index -> when (index){
   //         0 -> Box(modifier = Modifier.fillMaxSize()){
   //             firstSlide()
   //         }
   //         1 -> secondSlide()
   //         2 -> thirdSlider()
   //     }
   //     }
   // }
}}