package com.example.estatebook_app.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface EstateAPI {
    @GET("getEstatesOnMainPage")
    suspend fun getEstatesMainPage( //асинхронная функция которая может быть остановлена и продолжена позже
        @Query("page") page: Int,
        @Query("items_per_range") items_per_page: Int
    ): List<EstatesMainDto> //возвращаемый тип
    companion object{ //статическое поле-компаньон
        const val BASE_URL = "http://10.0.2.2:8000/"
    }
}