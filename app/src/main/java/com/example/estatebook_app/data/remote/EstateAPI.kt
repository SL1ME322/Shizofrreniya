package com.example.estatebook_app.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EstateAPI {
    @GET("getUserById/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<UserProfile>

    @GET("usersEstates")
    suspend fun usersEstates(
        @Query("id") id: Int
    ): Response<List<EstateMain>>

    @GET("/users/me/")
    abstract fun get_current_active_user(
        //@Header("Authorization") auth: String
    ):Call<UserProfile> //пофикси количество классов и вызывай из одного
    @FormUrlEncoded
    @POST("token")
    abstract fun login_for_access_token(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<TokenClass>
   @POST("addEstate/")
   abstract fun addEstate(
     @Body estateMain: EstateForPost
   ) : Call<EstateForPost>
    @POST("register_new_user/")
    abstract fun register_new_user(
        @Body userMain: UserMain
    ) : Call<UserMain>
    @GET("getEstatesOnMainPage")
    suspend fun getEstatesMainPage( //асинхронная функция которая может быть остановлена и продолжена позже
        @Query("page") page: Int,
        @Query("items_per_range") items_per_page: Int,
        @Query("search") name: String?,
    ): List<EstatesMainDto> //возвращаемый тип
    companion object{ //статическое поле-компаньон
        const val BASE_URL = "http://10.0.2.2:8000/"
    }
}