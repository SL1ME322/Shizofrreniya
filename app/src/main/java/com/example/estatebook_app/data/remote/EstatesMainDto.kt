package com.example.estatebook_app.data.remote

import com.google.gson.annotations.SerializedName

//dto - класс для передачи данных между клиентом и сервером
data class EstatesMainDto( //data class в отличие от класс автогенерирует геттеры и сохраняет одинаковую дату для всех экземпляров
   @SerializedName("ID_Estate")
    val id: Int,
    val Ad_Name: String,
    val Location: String,
    val Price: Int,
    val Area: Int,
    //val Rating: Double,
    //val Estate_Images_ID: Int
    )
