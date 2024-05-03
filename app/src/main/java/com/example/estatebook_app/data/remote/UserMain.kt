package com.example.estatebook_app.data.remote

data class UserMain(
    val ID_User: Int,
    //val Name: String?,
    //val Surname: String?,
    //val Middle_Name: Int?,
    //val Price_For_Month: Int,
    val Login: String,
    val hashed_password: String,
    val Avatar: String?,
    val Registration_Date: String?,
    //val Phone: String?,
    //val Location: String?,
    val Description: String?,
    //val Average_Mark: String?
)
