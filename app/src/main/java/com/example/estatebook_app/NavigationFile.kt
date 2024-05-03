package com.example.estatebook_app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.estatebook_app.data.remote.EstateMain
import com.squareup.moshi.Moshi


@Composable
fun SetupNavHost( ){
    val navController = rememberNavController()
    val viewModel = hiltViewModel<EstateViewModel>()

    //val viewModel = hiltViewModel<EstateViewModel>()
    NavHost(
        navController = navController,
        startDestination = "Welcome_Screen")
    {

        composable("Welcome_Screen") {
            mainFunc(navController)
        }
        composable("Authorize") {
            Authorize(navController)
        }
        composable("Register") {
            Register(navController)

        }
        composable(
            "EstatePage/estate={estate}"

        ) {
          navBackStackEntry ->  val estateJson = navBackStackEntry.arguments?.getString("estate")
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(EstateMain::class.java).lenient()
            val estateMainObj = jsonAdapter.fromJson(estateJson)
            EstatePage(estate = estateMainObj, navController = navController)
        }
        composable("MainPage") {
            MainPage(viewModel, navController)
        }
        composable("AddEstate") {
            addEstate2(navController)
        }
        composable("ProfilePage/{id}") {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toIntOrNull()
            ProfilePage(navController,id )
        }
        composable("MyProfilePage") {
            MyProfilePage(navController)
        }
    }

}