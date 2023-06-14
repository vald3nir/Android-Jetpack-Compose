package com.vald3nir.jetpack_compose.di

import android.content.Context
import com.vald3nir.jetpack_compose.data.networking.GamesService
import com.vald3nir.jetpack_compose.data.networking.logInterceptor
import com.vald3nir.jetpack_compose.domain.utils.Const
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun networkModule() = module {
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Const.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(
    context: Context,
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(logInterceptor(context))
        .build()
}

fun provideForecastApi(retrofit: Retrofit): GamesService = retrofit.create(GamesService::class.java)
