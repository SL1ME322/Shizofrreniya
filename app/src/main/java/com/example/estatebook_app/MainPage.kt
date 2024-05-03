package com.example.estatebook_app
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.estatebook_app.data.remote.name
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable

fun MainPage(viewModel: EstateViewModel, navController: NavController) {
    val filterState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }


    val context = LocalContext.current
    val estates = viewModel.estatePagingFlow.collectAsLazyPagingItems()
    //val estates2 = viewModel.searchByName.collectAsLazyPagingItems()
    BackHandler(enabled = filterState.isOpen) {
        scope.launch {
            filterState.close()
        }

    }

    LaunchedEffect(key1 = estates.loadState) { //безопасный запуск корутины, если key изменяется - то launchedeffect перезапускается
       name = "sqqe"
        if (estates.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Ошибка: " + (estates.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    ModalNavigationDrawer(drawerState = filterState, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier

                .then(
                    if
                            (filterState.targetValue == DrawerValue.Open)
                        Modifier.fillMaxWidth() else Modifier
                )
        ) {

            Column() {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_icon),
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp), contentDescription = ""
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                    Text("Фильтры")
                    Spacer(modifier = Modifier.fillMaxWidth(0.65f))
                    Button(
                        onClick = { /*TODO*/ }, enabled = true, shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                228,
                                164,
                                164,
                                0
                            ), contentColor = Color.White
                        )
                    ) {
                        Text("Сброс")
                    }
                }
                Divider()

            }
            Divider()
            var minPriceInputStart by remember { mutableStateOf("0") }
            var minPriceInputEnd by remember { mutableStateOf("100000000") }
            //var sliderMinPrice by remember {
            //    mutableStateOf(0f)
            var sliderMinPrice by remember {
                mutableStateOf(0f..100000000f)
            }
            //var minAreaStart by remember { mutableStateOf("0")}
            //var minAreaEnd by remember { mutableStateOf("10000f")}
            //var sliderArea by remember { //mutableStateOf(0f, 100000000f)
            //}
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            startY = 0f,
                            endY = 2000f,
                            tileMode = TileMode.Clamp,
                            colors = listOf(Color(94, 178, 148), Color(21, 225, 176, 200))
                        )
                    )
            ) {
                Filter()
            }

            NavigationDrawerItem(
                label = { /*TODO*/ }, selected = false, onClick = { /*TODO*/ })
        }


    }, gesturesEnabled = true, content = {
        Scaffold(floatingActionButton = { FloatingActionButton(onClick = { navController.navigate("addEstate") }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "icon")
        }}) {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = (listOf(
                            Color(162, 247, 178, 255),
                            Color(84, 230, 116, 5),

                            ))
                    )
                )
            ) {

                Column() {
                    //Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                    Row(horizontalArrangement = Arrangement.Center) {
                        val text = remember {
                            mutableStateOf("")
                        }
                        //Spacer(modifier = Modifier.fillMaxWidth(0.01f))
                        IconButton(modifier = Modifier
                            .size(70.dp, 70.dp)
                            .offset(0.dp, -5.dp), onClick =
                        {
                                val navigationRoute = "MyProfilePage"
                            println("Navigating to: $navigationRoute")
                            navController.navigate(navigationRoute)
                        }, content = {
                            Icon(
                                modifier = Modifier.size(43.dp, 43.dp),
                                painter = painterResource(id = R.drawable.baseline_format_align_justify_24),
                                contentDescription = "flfl"
                            )
                        })
                        //Spacer(modifier = Modifier.fillMaxWidth(0.08f))
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(
                                    Color.White,
                                    shape = RoundedCornerShape(35.dp)
                                ),
                            value = text.value,
                            placeholder = { Text(text = "Поиск...", fontSize = 24.sp) },
                            onValueChange = { newText -> text.value = newText
                                _searchQuery.value = newText},
                            shape = RoundedCornerShape(32.dp),

                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(32.dp, 32.dp),
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "dld"
                                )
                            },
                            singleLine = true,

                            colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.Transparent)
                        )
                        //Spacer(modifier = Modifier.fillMaxWidth(0.08f))

                    }


//            Box(modifier = Modifier.fillMaxSize()) {
//                if (estates.loadState.refresh is LoadState.Loading) {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                }
//
//            }


                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        //items(estates) {  estate -> if (estate != null){
                        //    EstateItem(estate = estate, modifier = Modifier.fillMaxSize())
                        //}
                        item {
                            Row(
                                modifier = Modifier.padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                                Image(
                                    painter = painterResource(id = R.drawable.mapsicon),
                                    contentDescription = "dlsdkks",
                                    modifier = Modifier
                                        .size(65.dp)
                                        .padding(end = 8.dp)
                                )

                                Text(
                                    text = "г.Москва", fontSize = 28.sp,
                                    fontWeight = FontWeight(600)
                                )
                            }
                            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
                            Text(
                                modifier = Modifier.padding(start = 32.dp, bottom = 10.dp),
                                text = "53 объявления найдено",
                                fontSize = 24.sp,
                                fontWeight = FontWeight(400)
                            )

                            Row() {
                                IconButton(
                                    onClick = { scope.launch { filterState.open() } },
                                    modifier = Modifier
                                        .fillMaxHeight(0.1f)
                                        .fillMaxWidth(0.1f)
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id =
                                            R.drawable.filterbutton
                                        ),
                                        contentDescription = "dkskdk"
                                    )
                                }
                            }

                        }
                        items(estates.itemCount, key = estates.itemKey { it.ID_Estate }) {//перебор листа
                                index ->
                            val item = estates[index]
                            if (item != null) {
                                EstateItem(
                                    estate = item,
                                    modifier = Modifier.fillMaxWidth(), navController
                                )
                                //Box(modifier = Modifier
                                //    .background(color = Color.Red)
                                //    .fillMaxWidth()) {
                                //    Text(text = item.Ad_Name)
                                //}
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                        item {
                            if (estates.loadState.append is LoadState.Loading) {
                                //CircularProgressIndicator()
                            }
                        }

                    }

                }
            }

        }
    })
}