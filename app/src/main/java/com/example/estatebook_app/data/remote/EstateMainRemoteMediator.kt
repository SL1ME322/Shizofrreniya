package com.example.estatebook_app.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.estatebook_app.EstateDatabase
import com.example.estatebook_app._searchQuery
import com.example.estatebook_app.data.local.EstateMainEntity
import com.example.estatebook_app.toEstateMainEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

var name = ""
@OptIn(ExperimentalPagingApi::class)
class EstateMainRemoteMediator (
    private val estateDb : EstateDatabase,
    private val estateApi: EstateAPI
): RemoteMediator<Int, EstateMainEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EstateMainEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    } else {
                        (lastItem.ID_Estate / state.config.pageSize) + 1
                    }
                }
            }

            delay(2000)

            val response: Response<List<EstatesMainDto>> = estateApi.getEstatesMainPage(
                page = loadKey,
                items_per_page = state.config.pageSize,
                name = _searchQuery.value
            )

            if (response.isSuccessful) {
                val estates = response.body()
                estateDb.withTransaction {
                    val estatesEntities = estates?.map { it.toEstateMainEntity() }
                    estateDb.dao.upsertAll(estatesEntities ?: emptyList())
                }
                MediatorResult.Success(
                    endOfPaginationReached = estates.isNullOrEmpty()
                )
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                MediatorResult.Error(IOException("Failed to load data: ${response.code()}. Error message: $errorMessage"))
            }
        } catch (e: IOException){
            MediatorResult.Error(e)
        } catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}