package com.example.estatebook_app

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.LazyPagingItems
import com.example.estatebook_app.data.remote.EstateMain
import com.example.estatebook_app.data.remote.EstateMainRemoteMediator


@Composable
fun SetupNavHost( ){
    val navController = rememberNavController()

    //val viewModel = hiltViewModel<EstateViewModel>()
    NavHost(
        navController = navController,
        startDestination = "MainPage")
    {
        var estates =
        composable("Welcome_Screen") {
            mainFunc(navController)
        }
        composable("Authorize") {
            Authorize(navController)
        }
        composable("Register") {
            Register(navController)
        }
       //composable("MainPage"){
       //    MainPage(navController  )
       //}
    }

}


