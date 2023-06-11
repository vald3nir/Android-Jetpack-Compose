package com.vald3nir.jetpack_compose.presentation.fragmets.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.domain.use_cases.GamesUseCases
import kotlinx.coroutines.flow.Flow

class HomeViewModel(gamesUseCases: GamesUseCases) : ViewModel() {

    val gamesListState: Flow<PagingData<GamesDTO>> =
        gamesUseCases.getAllGames().cachedIn(viewModelScope)
}