package com.vald3nir.jetpack_compose

import android.app.Application
import com.vald3nir.jetpack_compose.di.appModule
import com.vald3nir.jetpack_compose.di.networkModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppApplication)
            modules(
                listOf(
                    appModule(),
                    networkModule(),
                )
            )
        }
    }
}