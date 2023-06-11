package com.vald3nir.jetpack_compose.presentation.fragmets.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.domain.use_cases.GamesUseCases
import com.vald3nir.jetpack_compose.domain.utils.Response
import kotlinx.coroutines.launch

class DetailViewModel(private val gamesUseCases: GamesUseCases) : ViewModel() {

    private val _gamesState = mutableStateOf<Response<GamesDTO>>(Response.Success(null))
    val gamesState: State<Response<GamesDTO>> = _gamesState

    fun getDetailGames(id: Int) {
        viewModelScope.launch {
            gamesUseCases.getDetailGames(id).collect { response ->
                _gamesState.value = response
            }
        }
    }
}