package com.example.estatebook_app


import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.estatebook_app.data.remote.EstateAPI
import com.example.estatebook_app.data.remote.TokenClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

var token  = TokenClass("","")

@Composable
fun Authorize(navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    )
    {
        MaterialTheme(   ) {

            AuthorizationBox(navController)
        }

    }

}

@Composable
fun AuthorizationBox(navController: NavController) {
    val login by remember { mutableStateOf("")}
    val password by remember { mutableStateOf("")}
    val coroutineScope = rememberCoroutineScope()
    var apiUrl by remember { mutableStateOf(TextFieldValue("https://api.api-ninjas.com/v1/aircraft?manufacturer=Gulfstream")) }
    var apiKey by remember { mutableStateOf("1SWviemGM6duZ8HbHHr4YA==1cf65DmPvYOsPd6k") }
    var isConnected by remember { mutableStateOf(false) }
    Column(  ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(0.2f)
                .background(Color(131, 171, 231, 255)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.city),
                modifier = Modifier
                    .fillMaxHeight()
                    .offset(0.dp, 15.dp)
                    .fillMaxWidth(),
                contentDescription = "city",
                contentScale = ContentScale.FillBounds

            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(0.5f)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
                    color = Color(149, 229, 111, 255)
                )
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                val text = remember { mutableStateOf("") }
                val password = remember { mutableStateOf("") }
                var passwordVisibility by remember {
                    mutableStateOf(false)
                }
                var passwordIcon = if (passwordVisibility)
                    painterResource(id = R.drawable.baseline_visibility_24)
                else
                    painterResource(id = R.drawable.baseline_visibility_off_24)
                Spacer(modifier = Modifier.fillMaxHeight(0.03f))
                Text(
                    text = "С возвращением !", fontWeight = FontWeight(600),
                    fontSize = 25.sp, style = TextStyle(letterSpacing = 2.sp),
                    modifier = Modifier.align(CenterHorizontally),
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.8f),
                    value = text.value,
                    label = {
                        Text(
                            text = "Email"
                        )
                    },
                    onValueChange = { newText -> text.value = newText },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color(
                            65,
                            19,
                            196,
                            255
                        ),
                        focusedLabelColor = Color(95, 65, 182, 255),
                        unfocusedLabelColor = Color(70, 38, 160, 255),
                    ),
                    maxLines = 2,
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email Icon", tint = Color(
                                    65,
                                    19,
                                    196,
                                    255),
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { text.value = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Email Icon",
                                tint = Color(
                                    65,
                                    19,
                                    196,
                                    255),
                            )
                        }
                    })
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.8f),
                    value = password.value,
                    label = {
                        Text(
                            text = "Password"
                        )
                    },
                    onValueChange = { newText -> password.value = newText },
                    singleLine = true,
                    maxLines = 2,
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color(
                            65,
                            19,
                            196,
                            255
                        ),
                        focusedLabelColor = Color(95, 65, 182, 255),
                        unfocusedLabelColor = Color(70, 38, 160, 255),
                    ),
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Lock, tint = Color(
                                    65,
                                    19,
                                    196,
                                    255
                                ),
                                contentDescription = "Email Icon"
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(painter = passwordIcon, contentDescription = "sksdjsk", tint = Color(
                                65,
                                19,
                                196,
                                255
                            )
                            )
                        }

                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                )
//                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
//                Box( modifier = Modifier.align(End)) {
//                    Text(modifier = Modifier.offset(-20.dp, 0.dp),color = Color.Blue, fontSize = 18.sp,  text = "Забыли пароль ?",  )
//                }
//
//                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.16f),
                    colors = ButtonDefaults.buttonColors(Color(234, 168, 42, 255)),
                    onClick = {  Authenticate(text.value,password.value, navController)

                         }, shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Войти", color = Color.Black, fontSize = 21.sp)

                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.16f),
                    colors = ButtonDefaults.buttonColors(Color(234, 168, 42, 255)),
                    onClick = {
                        coroutineScope.launch {
                            // Call checkConnectionToAPI function within a coroutine scope
                            //isConnected = checkConnectionToAPI(apiUrl.text, apiKey)
                            //checkServerAvailability()
                            getJsonDataFromServer("http://10.0.2.2:8080/estates/getEstatesOnMainPage?page=1&items_per_range=10&search=");
                            Authenticate(login, password.value, navController);
                        }
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Вывести", color = Color.Black, fontSize = 21.sp)
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.12f))
                Row(){
                    ClickableText(
                        AnnotatedString("Нет аккаунта ? "),
                        style = TextStyle(fontSize = 19.sp,  ),
                        onClick = { })
                    ClickableText(
                        AnnotatedString("Зарегистрироваться"),
                        style = TextStyle(fontSize = 19.sp, color = Color.Blue),
                        onClick = {navController.navigate("Register")})
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.12f))
                Row( horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {

                    Image(
                        painterResource(id = R.drawable.google_logo),
                        modifier = Modifier.size(52.dp),
                        contentDescription = "GoogleLogo"
                    )
                    Image(
                        painterResource(id = R.drawable.twitter_logo),
                        modifier = Modifier.size(52.dp),
                        contentDescription = "TwitterLogo"
                    )
                    Image(
                        painterResource(id = R.drawable.facebook_logo),
                        modifier = Modifier.size(52.dp),
                        contentDescription = "FacebookLogo"
                    )
                }
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    val path = Path()
                    path.moveTo(0f, size.height)
                    path.lineTo(0f, size.height * 0.8f)
                    path.quadraticBezierTo(
                        size.width * 0.45f,
                        size.height * 0.9f,
                        size.width * 0.6f,
                        size.height * 0.6f
                    )
                    path.quadraticBezierTo(
                        size.width * 0.81f,
                        size.height * -0.05f,
                        size.width,
                        size.height * -0.07f
                    )

                    path.lineTo(size.width, size.height * 1.6f)
                    drawPath(path = path,
                        Brush.horizontalGradient(
                            startX = 0f,
                            endX = 2000f,
                            colors = listOf(Color(69, 153, 143, 15), Color(11, 45, 110, 255))
                        )
                    )
                }

            }

        }


    }
}

suspend fun checkConnectionToAPI(apiUrl: String, apiKey: String): Boolean {
    val client = OkHttpClient()

    return try {
        val request = Request.Builder()
            .url( "https://api.api-ninjas.com/v1/aircraft?manufacturer=Gulfstream&model=G550")
            .addHeader("X-Api-Key", apiKey)

            .build()

        withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            val responseCode = response.code
            println("Response Code: $responseCode")
            println("Response Code: ${response.message}")
            println("Response Code: ${response.body}")
            println("Response Code: ${response.isSuccessful}")
            responseCode == 200
        }
    } catch (e: IOException) {
        println("Exception: ${e.message}")
        println("Exception: ${e.message}")
        println("Exception: ${e.cause}")
        println("Exception: ${e.stackTrace}")
        println("Exception: ${e.localizedMessage}")
        false
    }
}
suspend fun getUsersFromApi() {
    try {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient()) // Добавляем клиент OkHttp
            .build()
        val api = retrofit.create(EstateAPI::class.java)
        val response = withContext(Dispatchers.IO) {
            api.getAllUsers()
        }
        if (response.isSuccessful) {
            val users = response.body()
            users?.forEach {
                println(it) // Вывод пользователей в консоль
            }
        } else {
            println("Ошибка при получении пользователей: ${response.code()}")
        }
    } catch (e: Exception) {
        println("Произошла ошибка: ${e.message}")
    }
}
suspend fun checkServerAvailability(): Boolean {
    val client = OkHttpClient()
    return withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("http://10.0.2.2:8000/")

                .build()

            val response = client.newCall(request).execute()
            val responseCode = response.code
            println("Response Code: $responseCode")
            responseCode == 200
        } catch (e: IOException) {
            println("Exception: ${e.message}")
            false
        }
    }
}

fun Authenticate(login: String, password: String, navController: NavController) {
    GlobalScope.launch(Dispatchers.IO) {
        try {

            val url = URL("${EstateAPI.BASE_URL}/login")
            val connection = url.openConnection() as HttpURLConnection


            connection.requestMethod = "POST"


            val authHeaderValue = "Basic " + Base64.encodeToString("$login:$password".toByteArray(), Base64.NO_WRAP)
            connection.setRequestProperty("Authorization", authHeaderValue)


            val responseCode = connection.responseCode
            val responseMessage = connection.responseMessage
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()


            if (responseCode == HttpURLConnection.HTTP_OK) {

                withContext(Dispatchers.Main) {
                    navController.navigate("MainPage")
                    Toast.makeText(navController.context, "Вы авторизовались!", Toast.LENGTH_LONG).show()
                }
            } else {

                withContext(Dispatchers.Main) {
                    Toast.makeText(navController.context, "Ошибка аутентификации: $responseMessage", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: IOException) {
            Log.e("Authenticate", "Exception: ${e.message}", e)
            withContext(Dispatchers.Main) {
                Toast.makeText(navController.context, "Произошла ошибка: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

fun getJsonDataFromServer(url: String) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("Failed to execute request: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            val responseData = response.body?.string()
            println("Response JSON data:")
            println(responseData)
        }
    })
}

