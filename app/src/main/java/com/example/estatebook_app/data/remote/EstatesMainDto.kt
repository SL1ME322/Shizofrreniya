package com.example.estatebook_app.data.remote

import com.google.gson.annotations.SerializedName
import java.util.*

//dto - класс для передачи данных между клиентом и сервером
data class EstatesMainDto( //data class в отличие от класс автогенерирует геттеры и сохраняет одинаковую дату для всех экземпляров
 @SerializedName("ID_Estate")
   val ID_Estate: Int,
 val Ad_Name: String,
 val Location: String,
 val Price: Int,
 val Price_For_Month: Int,
 val Mortgage_Price: Int,
 val Area: Int,
 val House_Area: Int,
 val Metro_Station: String,
 val Train_Station: String,
 val Description: String,
 val Ad_Date: String?,
 val Building_Date: String?,
 val Status: String,
 //val Estate_Rating: Int,
 val Estate_Images_ID: Int,
  val User_ID: Int
    )
