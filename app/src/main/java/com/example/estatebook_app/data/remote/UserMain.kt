package com.example.estatebook_app.data.remote

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class UserMain(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("middleName") val middleName: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("location") val location: String,
    @SerializedName("description") val description: String,
    @SerializedName("averageMark") val averageMark: Float,
    @SerializedName("registrationDate") val registrationDate: LocalDateTime?,
    @SerializedName("status") val status: StatusEnum,
    @SerializedName("isBanned") val isBanned: Boolean,
    @SerializedName("roles") val roles: RoleEnum,
    @SerializedName("bannedUntilDate") val bannedUntilDate: LocalDateTime?,
    @SerializedName("complaintAmounts") val complaintAmounts: Int?
)