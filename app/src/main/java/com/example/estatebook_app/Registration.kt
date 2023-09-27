package com.example.estatebook_app


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Register(navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    )
    {
        MaterialTheme(   ) {

            RegisrationBox(navController)
        }

    }

}

@Composable
fun CustomCheckBox() {
    var checked by remember { mutableStateOf(false) }
    Box(  ) {
        Row(   verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked,
                onCheckedChange = { isChecked -> checked = isChecked })
            ClickableText(
                AnnotatedString("Запомнить пароль ?"),
                style = TextStyle(fontSize = 19.sp,  ),
                onClick = {   checked =! checked})
        }
    }


}

@Composable
fun RegisrationBox(navController: NavController) {
    Column(  ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(0.2f)
                .background(Color(131, 171, 231, 255)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.house_estate),
                modifier = Modifier
                    .fillMaxHeight()
                    .offset(0.dp, 10.dp)
                    .scale(scaleX = 1.15f, scaleY = 1.12f)
                    .fillMaxWidth(),

                contentDescription = "city",
                contentScale = ContentScale.FillBounds

            )
        }

        Box(

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(0.55f)

                .fillMaxWidth()

                .background(
                    shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(244, 219, 154, 255),
                            Color(239, 194, 78, 155)
                        )
                    )
                )
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()

                ) {
                    val path = Path()
                    path.moveTo(0f, size.height )
                    path.lineTo(0f, size.height * 0.9f)
                    path.quadraticBezierTo(
                        size.width * 0.45f,
                        size.height * 1.0f,
                        size.width * 0.7f,
                        size.height * 0.8f
                    )
                    path.quadraticBezierTo(
                        size.width * 0.85f,
                        size.height *  0.65f,
                        size.width,
                        size.height *  0.7f
                    )

                    path.lineTo(size.width, size.height )
                    drawPath(path = path,
                        Brush.horizontalGradient(
                            startX = 0f,
                            endX = 1300f,
                            colors = listOf(Color(69, 153, 143, 15), Color(11, 45, 110, 255))
                        )
                    )
                }
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
                        //Spacer(modifier = Modifier.fillMaxHeight(0.0001f))
                Spacer(modifier = Modifier.fillMaxHeight(0.03f))
                Text(
                    text = "Регистрация", fontWeight = FontWeight(600),
                    fontSize = 25.sp, style = TextStyle(letterSpacing = 2.sp),
                    modifier = Modifier.align(CenterHorizontally),
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.01f))
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
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.8f),
                    value = password.value,
                    label = {
                        Text(
                            text = "Confirm Password"
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
                                imageVector = Icons.Default.Check, tint = Color(
                                    65,
                                    19,
                                    196,
                                    255
                                ),
                                contentDescription = "Email Icon"
                            )
                        }
                    },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.02f))
                Box(modifier = Modifier
                    .align(Start)
                    .offset(27.dp, 0.dp)) {
                    CustomCheckBox( )
                }

                Spacer(modifier = Modifier.fillMaxHeight(0.01f))

                Box( modifier = Modifier.align(End)) {
                    Text(modifier = Modifier.offset(0.dp, 0.dp),
                        color = Color.Blue, fontSize = 18.sp,
                        text = "Забыли пароль ?",  )
                }

                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.25f),
                    colors = ButtonDefaults.buttonColors(Color(234, 168, 42, 255)),
                    onClick = {navController.navigate("MainPage")}, shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Зарегистрироваться", color = Color.Black, fontSize = 21.sp)

                }
                Spacer(modifier = Modifier.fillMaxHeight(0.12f))
                Row(){
                    ClickableText(
                        AnnotatedString("Есть аккаунт ? "),
                        style = TextStyle(fontSize = 19.sp,  ),
                        onClick = { })
                    ClickableText(
                        AnnotatedString("Войти"),
                        style = TextStyle(fontSize = 19.sp, color = Color.Blue),
                        onClick = {navController.navigate("Authorize")})
                }

                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
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


            }

        }


    }
}