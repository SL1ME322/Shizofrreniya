package com.example.estatebook_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.estatebook_app.data.remote.EstateMain
import com.squareup.moshi.Moshi


@OptIn(ExperimentalFoundationApi::class)
@Composable
 fun EstateItem(
    estate: EstateMain,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val pagerState = rememberPagerState { 3 }
    val ROUTE_ESTATE_DETAILS = "EstatePage/estate={estate}"
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(EstateMain::class.java).lenient()
    val estateJson = jsonAdapter.toJson(estate)

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