package com.example.estatebook_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

//entity для локальной бд
@Entity(tableName = "EstateMainEntity")
data class EstateMainEntity(
    @PrimaryKey
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
