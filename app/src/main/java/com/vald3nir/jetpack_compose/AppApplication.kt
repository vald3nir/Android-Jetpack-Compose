package com.vald3nir.jetpack_compose

import android.app.Application
import com.vald3nir.jetpack_compose.data.networking.GamesService
import com.vald3nir.jetpack_compose.data.repository.GamesRepository
import com.vald3nir.jetpack_compose.data.repository.GamesRepositoryImpl
import com.vald3nir.jetpack_compose.domain.use_cases.GamesUseCases
import com.vald3nir.jetpack_compose.domain.use_cases.GamesUseCasesImpl
import com.vald3nir.jetpack_compose.domain.utils.Const
import com.vald3nir.jetpack_compose.presentation.fragmets.detail.DetailViewModel
import com.vald3nir.jetpack_compose.presentation.fragmets.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppApplication)
            modules(listOf(appModule()))
        }
    }


    private fun provideRetrofit(
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder()
            .baseUrl(Const.WEB_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    private fun appModule() = module {

        viewModel { HomeViewModel(get()) }
        viewModel { DetailViewModel(get()) }

        single<GamesRepository> {
            GamesRepositoryImpl(
                gamesService = provideRetrofit().create(GamesService::class.java),
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

}