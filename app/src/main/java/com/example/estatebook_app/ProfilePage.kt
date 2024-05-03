package com.example.estatebook_app

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.estatebook_app.data.remote.EstateAPI
import com.example.estatebook_app.data.remote.UserProfile
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




var profileLoaded3 = mutableStateOf(false)
@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfilePage(navController: NavController, id: Int?) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    var userProfileAd by remember{ mutableStateOf<UserProfile?>(null)}


    Scaffold(modifier = Modifier.fillMaxWidth()) {
        Column() {

            Box {
                LaunchedEffect(key1 = id) {

                  userProfileAd= getUserById(navController, id!!)
                    delay(1000)

                }

                Column(modifier = Modifier.fillMaxSize(1f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    if (profileLoaded3.value ){
                        userProfileAd.let {
                            LaunchedEffect(key1 = userProfileAd ){
                                list.value = getUserEstates(navController, userProfileAd!!.ID_User)!!
                            }

                            val byteArray = Base64.decode(it?.Avatar, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                            Image(bitmap = bitmap.asImageBitmap(),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(250.dp)
                                    .border(2.dp, Color.Gray, CircleShape)
                                    .align(Alignment.CenterHorizontally)
                                    .clip(
                                        CircleShape
                                    ), contentDescription = "sas" )

                            Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = userProfileAd!!.Login,
                                style = TextStyle(fontSize = 26.sp)
                            )

                            Text(
                                text = "Объявления:",
                                style = TextStyle(fontSize = 26.sp)
                            )


                            LazyColumn{ Modifier.fillMaxHeight(0.3f)
                                //items(list.value){ list -> Text(text = list.Ad_Name)}
                                items(list.value.size ) {//перебор листа
                                        index ->
                                    val item = list.value[index]
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
                            }



                        }


                    }


                }


            }
        }
    }
}


suspend fun getUserById(navController:NavController, id: Int): UserProfile? {
    try{
        val client = OkHttpClient.Builder().apply {
            addInterceptor(object: Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer ${token.access_token}")
                        .build()
                    return chain.proceed(request)
                }
            })
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(EstateAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(EstateAPI::class.java)
        val response = api.getUserById(id)
        if (response.isSuccessful) {
            profileLoaded3.value = true
            return response.body()
        } else {
            Toast.makeText(navController.context, response.code().toString(), Toast.LENGTH_LONG).show()
            return null
        }
    }
    catch(ex:Exception){
        return null
    }

}