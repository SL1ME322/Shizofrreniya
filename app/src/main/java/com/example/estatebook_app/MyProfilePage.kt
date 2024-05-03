package com.example.estatebook_app

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
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
import com.example.estatebook_app.data.remote.EstateAPI
import com.example.estatebook_app.data.remote.EstateMain
import com.example.estatebook_app.data.remote.UserProfile
import kotlinx.coroutines.delay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var userProfile = mutableStateOf<UserProfile?>(null)
var profileLoaded = mutableStateOf(false)
var profile2Loaded = mutableStateOf(false)
val list = mutableStateOf(listOf<EstateMain>())


@Composable
fun MyProfilePage(navController: NavController) {

    Scaffold(modifier = Modifier.fillMaxWidth()) {
        Column() {

            Box {
                LaunchedEffect(key1 = userProfile) {


                    getProfile(navController)
                    delay(1000)
                }
                Column(modifier = Modifier.fillMaxSize(1f),
                    verticalArrangement = Arrangement.Center,
                     ) {
                    if (profileLoaded.value) {

                        userProfile.let {
                                LaunchedEffect(key1 = userProfile ){
                                    list.value = getUserEstates(navController, userProfile.value!!.ID_User)!!
                                }
                                val byteArray = Base64.decode(it.value?.Avatar, Base64.DEFAULT)
                                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(250.dp)
                                        .border(2.dp, Color.Gray, CircleShape).align(Alignment.CenterHorizontally)
                                        .clip(
                                            CircleShape
                                        ), contentDescription = "sas"
                                )
                            Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = userProfile.value!!.Login,
                                style = TextStyle(fontSize = 26.sp)
                            )

                                Text(
                                    text = "Объявления:",
                                    style = TextStyle(fontSize = 26.sp)
                                )
                            if (profile2Loaded.value){

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
}


suspend fun getProfile(navController:NavController)
{
    val client = OkHttpClient.Builder().apply {
        addInterceptor(object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${token.access_token}" )
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
    val call: Call<UserProfile> = api.get_current_active_user()
    call.enqueue(object: Callback<UserProfile> {
        override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {

            val responseCode = response.code()
            if (responseCode != 200){
                return Toast.makeText(navController.context ,response.code().toString(), Toast.LENGTH_LONG).show()
            }
            else{

                if(response.isSuccessful){
                    userProfile.value = response.body()!!
                    profileLoaded.value = true
                }


            }
        }
        override fun onFailure(call: Call<UserProfile>, t: Throwable) {
            t.message
        }
    })


}

suspend fun getUserEstates(navController:NavController, id: Int): List<EstateMain>? {
    val client = OkHttpClient.Builder().apply {
        addInterceptor(object : Interceptor {
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

    // Call the Retrofit API using a coroutine instead of enqueue(), and directly return the body() if the request is successful.
    val response = api.usersEstates(id)
    if (response.isSuccessful) {
        list.value = response.body()!!
        profile2Loaded.value = true
        return response.body() // Note that this will need to handle null result data.
    } else {
        // Handle Error Here.
        Toast.makeText(navController.context, response.code().toString(), Toast.LENGTH_LONG).show()
        return null
    }
}