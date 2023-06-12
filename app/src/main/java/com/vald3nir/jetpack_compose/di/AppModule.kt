package com.vald3nir.jetpack_compose.di

import com.vald3nir.jetpack_compose.data.repository.GamesRepository
import com.vald3nir.jetpack_compose.data.repository.GamesRepositoryImpl
import com.vald3nir.jetpack_compose.domain.use_cases.GamesUseCases
import com.vald3nir.jetpack_compose.domain.use_cases.GamesUseCasesImpl
import com.vald3nir.jetpack_compose.domain.utils.Const
import com.vald3nir.jetpack_compose.presentation.fragmets.detail.DetailViewModel
import com.vald3nir.jetpack_compose.presentation.fragmets.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun appModule() = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }

    single<GamesRepository> {
        GamesRepositoryImpl(
            gamesService = get(),
            pageSize = Const.PAGE_SIZE,
        )
    }

    single<GamesUseCases> {
        GamesUseCasesImpl(
            apiKey = Const.KEY_API,
            repository = get()
        )
    }
}