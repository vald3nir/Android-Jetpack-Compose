package com.vald3nir.jetpack_compose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.data.networking.GamesPagingSource
import com.vald3nir.jetpack_compose.data.networking.GamesService
import com.vald3nir.jetpack_compose.domain.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GamesRepositoryImpl(
    private val gamesService: GamesService,
    private val pageSize: Int,
) : GamesRepository {

    override fun getAllGames(apiKey: String): Flow<PagingData<GamesDTO>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            GamesPagingSource(
                response = { pageNext ->
                    gamesService.getAllGames(
                        key = apiKey,
                        page = pageNext,
                        pageSize = pageSize,
                    )
                }
            )
        }
    ).flow

    override fun getDetailGames(apiKey: String, id: Int): Flow<Response<GamesDTO>> = flow {
        try {
            emit(Response.Loading)
            val responseApi = gamesService.getGamesDetail(
                key = apiKey,
                id = id
            )
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}