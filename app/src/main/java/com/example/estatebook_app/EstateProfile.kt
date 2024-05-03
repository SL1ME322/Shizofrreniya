package com.example.estatebook_app

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.estatebook_app.data.remote.EstateAPI
import com.example.estatebook_app.data.remote.UserProfile
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    @Composable
    fun ProfileOnEstate( id_user: Int, navController: NavController, modifier: Modifier) {
        var user by remember { mutableStateOf<UserProfile?>(null)}
        Box(
            modifier
                .fillMaxWidth(0.87f)

                .clip(shape = RoundedCornerShape(30.dp))
                .background(Color.White)


        ) {
            Row(
                modifier = Modifier.padding(10.dp),

                horizontalArrangement = Arrangement.Center,
            ) {
            Column() {
                LaunchedEffect(key1 = id_user){
                    user = getUserByID(navController = navController, id_user   )
                }
                user?.let {
                    Row(Modifier.fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {

                        val byteArray = Base64.decode(user?.Avatar ?: "https://cdn2.iconfinder.com/data/icons/social-flat-buttons-3/512/anonymous-512.png", Base64.DEFAULT)
                        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                        Image(bitmap =bitmap.asImageBitmap() , contentDescription = "",
                            contentScale = ContentScale.Crop,

                            modifier = Modifier
                                .size(68.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterVertically))
                        Spacer(modifier = Modifier.fillMaxWidth(0.05f))

                        Column(verticalArrangement = Arrangement.Center, ) {
                            Text(
                                text = user?.Login ?: "" ,
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "ВЛАДЕЛЕЦ",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(61, 128, 228),
                                    fontSize = 22.sp,
                                    letterSpacing = 5.sp,
                                ),
                                modifier = Modifier.offset(x = -2.dp,y = -6.dp )

                            )
                        }
                        Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                        Image(
                            painter = painterResource(id = R.drawable.message),

                            contentDescription = "",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 3.dp, end = 3.dp)
                                .align(Alignment.Top)
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth(1f)
                            .padding(top = 12.dp)) {
                        Text(text = user?.Description ?: "",  style = TextStyle(color = Color.Gray, fontWeight = FontWeight.SemiBold),
                        )
                    }
                }

            }
        



        }
    }
}
suspend fun getUserByID(navController: NavController, id: Int): UserProfile? {
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

        // Call the Retrofit API using a coroutine instead of enqueue(), and directly return the body() if the request is successful.
        val response = api.getUserById(id)
        if (response.isSuccessful) {


            return response.body()!! // Note that this will need to handle null result data.
        } else {
            // Handle Error Here.
            Toast.makeText(navController.context, response.code().toString(), Toast.LENGTH_LONG).show()
            return null
        }

    }
    catch (e: java.lang.Exception){
        return null
    }

}