package com.example.estatebook_app.data.remote
import kotlinx.serialization.Serializable
@Serializable
data class UserProfile(
    var ID_User: Int,
    //val Name: String?,
    //val Surname: String?,
    //val Middle_Name: Int?,
    //val Price_For_Month: Int,
    var Login: String,
    var Avatar: String?,
    //val Phone: String?,
    //val Location: String?,
     val Description: String?,
    //val Average_Mark: String?
)