package com.example.estatebook_app

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.estatebook_app.data.local.EstateMainEntity

@Dao
//интерфейс для создания запросов
interface EstateMainDao {
    @Upsert
    suspend fun upsertAll(estates: List<EstateMainEntity>) //вставка листа недвижимостей (или обновление)

    @Query("Select * from EstateMainEntity")
    fun pagingSource(): PagingSource<Int, EstateMainEntity> //метод вызова пагинированных данных из бд (номер, запись)

    @Query("Delete from EstateMainEntity")
    suspend fun clearAll()
}