package com.example.estatebook_app.data.remote

import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.estatebook_app.EstateDatabase
import com.example.estatebook_app.data.local.EstateMainEntity
import com.example.estatebook_app.toEstateMainEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
 class EstateMainRemoteMediator ( //remote mediator нужен для загрузки данных из api в db с пагинацией
    private val estateDb : EstateDatabase,
    private val estateApi: EstateAPI
):RemoteMediator<Int, EstateMainEntity>(){ //<номер страницы, тип данных>
    @RequiresApi(34)
    override suspend fun load(
        loadType: LoadType, //разные типы загрузки (refresh, prepend, append)
        state: PagingState<Int, EstateMainEntity>
    ): MediatorResult { //результат загрузки данных
        return try{
            val loadKey = when(loadType){ //аналог switch
                LoadType.REFRESH -> 1 //при обновлении возвращение к 1 стр
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true ) //в приложении нет prepend
                    LoadType.APPEND ->{
                        val lastItem = state.lastItemOrNull() //последняя запись
                      if(lastItem == null){
                            return MediatorResult.Success(endOfPaginationReached = true) // возвращение первой страницы
                        }
                        else {
                            (lastItem.id / state.config.pageSize) + 1
                        }
                    }
            }
           // delay(2000)
            val estates = estateApi.getEstatesMainPage(page = loadKey, items_per_page = state.config.pageSize) //лист из api
            estateDb.withTransaction { //with transaction для нескольких команд sql (либо все будут выполнены, либо ни одна)
                if(loadType == LoadType.REFRESH){ //очистка кэша при обновлении
                    estateDb.dao.clearAll()
                }   //it - это каждый элемннт списка estates
                val estatesEntites = estates.map{it.toEstateMainEntity()} //изменение листа в лист записей для бд
                estateDb.dao.upsertAll(estatesEntites)
            }
            MediatorResult.Success(
                endOfPaginationReached = estates.isEmpty() //проверка api листа на пустоту
            )
        }
        catch (e: IOException){ // ошибка при чтении-записи
            MediatorResult.Error(e)

        }
        catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}
