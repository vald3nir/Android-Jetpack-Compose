package com.vald3nir.jetpack_compose.data.repository

import androidx.paging.PagingData
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.domain.utils.Response
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getAllGames(apiKey: String): Flow<PagingData<GamesDTO>>
    fun getDetailGames(apiKey: String, id: Int): Flow<Response<GamesDTO>>
}