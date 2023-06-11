package com.vald3nir.jetpack_compose.domain.use_cases

import androidx.paging.PagingData
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.domain.utils.Response
import kotlinx.coroutines.flow.Flow

interface GamesUseCases {
    fun getAllGames(): Flow<PagingData<GamesDTO>>
    fun getDetailGames(id: Int): Flow<Response<GamesDTO>>
}