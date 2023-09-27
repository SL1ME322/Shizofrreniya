package com.example.estatebook_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
//entity для локальной бд
@Entity(tableName = "EstateMainEntity")
data class EstateMainEntity(
    @PrimaryKey
    val id: Int,
    val Ad_Name: String,
    val Location: String,
    val Price: Int,
    val Area: Int,
    val Rating : Double,
   // val Estate_Images_ID: Int
)
