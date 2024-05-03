package com.example.estatebook_app
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.estatebook_app.data.remote.EstateAPI
import com.example.estatebook_app.data.remote.EstateForPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addEstate2(navController: NavController) {
    var selectedData by remember {
        mutableStateOf<Date?>(null)
    }
    var date by remember { mutableStateOf("2023-01-01") }
    var dateToDb by remember { mutableStateOf("") }
    var openDialog = remember { mutableStateOf(false) }
    var adname by remember { mutableStateOf("string") }
    var location by remember { mutableStateOf("string") }
    var price by remember { mutableStateOf("2500000") }
    var priceForMonth by remember { mutableStateOf("50000") }
    var mortgagePrice by remember { mutableStateOf("1200000") }
    var area by remember { mutableStateOf("100") }
    var house_area by remember { mutableStateOf("70") }
    var metro_station by remember { mutableStateOf("Станция метро Центральная") }
    var train_station by remember { mutableStateOf("Ближайшая станция поезда  Центральная") }
    var description by remember { mutableStateOf("Просторная и светлая квартира с видом на реку") }
    var ad_date2 = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    var ad_date by remember { mutableStateOf("70") }
    var building_date by remember { mutableStateOf("2020-01-01") }
    var status by remember { mutableStateOf("Активно") }
    //var rating by remember { mutableStateOf("5") }
    var estateImages by remember { mutableStateOf("1") }
    var user_ID by remember { mutableStateOf("1") }
    Scaffold() {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()),
             horizontalAlignment = Alignment.CenterHorizontally  ) {
            OutlinedTextField(value = adname, onValueChange = { adname = it })
            OutlinedTextField(value = location, onValueChange = { location = it })
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            OutlinedTextField(
                value = priceForMonth,
                onValueChange = { priceForMonth = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            OutlinedTextField(
                value = mortgagePrice,
                onValueChange = { mortgagePrice = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            OutlinedTextField(
                value = house_area,
                onValueChange = { house_area = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(value = metro_station, onValueChange = { metro_station = it })
            OutlinedTextField(value = train_station, onValueChange = { train_station = it })
            OutlinedTextField(value = description, onValueChange = { description = it })

            OutlinedTextField(value = date,
                readOnly = true,
                enabled = false,
                onValueChange = { date = it },
                modifier = Modifier.clickable(onClick = { openDialog.value = !openDialog.value }))
            if (openDialog.value) {
                DatePickerDialog(onDismissRequest = { //клик за окно выбора даты
                    openDialog.value = false
                },
                    confirmButton = {
                        TextButton(onClick = {
                            openDialog.value = false
                            //date = convertMillisForBuilding(ad_date2.selectedDateMillis)
                            //dateToDb = date + ""
                        }) {
                            Text(text = "OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { openDialog.value = false }) {
                            Text(text = "CANCEL")
                        }
                    })
                {
                    DatePicker(state = ad_date2)
                }
            }
            OutlinedTextField(value = status, onValueChange = { status = it })
           // OutlinedTextField(
           //     value = rating,
           //     onValueChange = { rating = it },
           //     keyboardOptions = KeyboardOp  tions(keyboardType = KeyboardType.Number)
           // )
            OutlinedTextField(
                value = estateImages,
                onValueChange = { estateImages = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            OutlinedTextField(
                value = user_ID,
                onValueChange = { user_ID = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
            Button(onClick = {

                AddEstate(estate = EstateForPost(
                    1,
                    Ad_Name = adname,
                    Location = location,
                    Price = price.toInt(),
                    Price_For_Month = priceForMonth.toInt(),
                    Mortgage_Price = mortgagePrice.toInt(),
                    Area = area.toInt(),
                    House_Area = house_area.toInt(),
                    Metro_Station = metro_station,
                    Train_Station = train_station,
                    Description = description,
                    // Укажите остальные поля в соответствии с введенными данными
                     Ad_Date = "2021-12-12", // Пример, если это поле имеет тип String?
                    Building_Date = "2021-12-12", // Пример, если это поле имеет тип String?
                    Status = status,
                    //Estate_Rating = rating.toInt(),
                    Estate_Images_ID = estateImages.toInt(),
                    User_ID = user_ID.toInt()
                ))
            }) {

            Text("Добавить")
            }


        }

    }
}

@SuppressLint("SimpleDateFormat")
fun convertMillisForBuilding(millis: Long?): String {
    return if (millis != null) {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        formatter.format(Date(millis))
    } else {
        ""
    }
}


fun AddEstate(estate: EstateForPost) {
    val retrofit = Retrofit.Builder()
        .baseUrl(EstateAPI.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(EstateAPI::class.java)
    val call: Call<EstateForPost> = api.addEstate(estate)
    call.enqueue(object : Callback<EstateForPost> {
        override fun onResponse(call: Call<EstateForPost>,
                                response: Response<EstateForPost>) {
            if (response.isSuccessful){
                response.body()
            }
            else{

            }
        }

        override fun onFailure(call: Call<EstateForPost>, t: Throwable) {
            t.message
        }

    })
}