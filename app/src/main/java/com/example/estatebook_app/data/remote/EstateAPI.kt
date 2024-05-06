package com.example.estatebook_app.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EstateAPI {
    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserMain>>
    @GET("getUserById/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<UserProfile>
    @FormUrlEncoded
    @POST("login")
    abstract fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Any>

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
        @POST("/register")
        abstract fun register_new_user(
            @Query("login") login: String,
            @Query("password") password: String,
            @Body userMain: UserMain
        ) : Call<ResponseBody>
    @GET("/estates/getEstatesOnMainPage")
    suspend fun getEstatesMainPage(
        @Query("page") page: Int,
        @Query("items_per_range") items_per_page: Int,
        @Query("search") name: String?,
    ): Response<List<EstatesMainDto>>
    companion object{ //статическое поле-компаньон
        const val BASE_URL = "http://10.0.2.2:8080"
    }
}