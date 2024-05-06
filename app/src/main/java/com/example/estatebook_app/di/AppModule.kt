package com.example.estatebook_app.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.estatebook_app.EstateDatabase
import com.example.estatebook_app.data.local.EstateMainEntity
import com.example.estatebook_app.data.remote.EstateAPI
import com.example.estatebook_app.data.remote.EstateMainRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideEstateDatabase(@ApplicationContext context: Context): EstateDatabase {
        return Room.databaseBuilder(context, EstateDatabase::class.java, "estates.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEstateApi(): EstateAPI {
        return Retrofit.Builder()
            .baseUrl(EstateAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideEstatePager(estateDb: EstateDatabase, estateApi: EstateAPI) :
            Pager<Int, EstateMainEntity> {
        return Pager(config = PagingConfig(pageSize = 4),
        remoteMediator = EstateMainRemoteMediator(
            estateDb = estateDb,
            estateApi = estateApi
        ),
            pagingSourceFactory = {
                estateDb.dao.pagingSource()
            }
        )
    }
}