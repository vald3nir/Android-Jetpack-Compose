package com.vald3nir.jetpack_compose.domain.use_cases

import androidx.paging.PagingData
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.data.repository.GamesRepository
import com.vald3nir.jetpack_compose.domain.utils.Response
import kotlinx.coroutines.flow.Flow

class GamesUseCasesImpl(
    private val apiKey: String,
    private val repository: GamesRepository,
) : GamesUseCases {

    override fun getAllGames(): Flow<PagingData<GamesDTO>> = repository.getAllGames(apiKey)

    override fun getDetailGames(id: Int): Flow<Response<GamesDTO>> =
        repository.getDetailGames(apiKey, id)
}