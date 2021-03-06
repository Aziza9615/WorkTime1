package com.example.worktime1.di

import android.app.Application
import com.example.worktime1.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules

class WorkTimeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@WorkTimeApp)
            inject()
        }
    }
    fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(
            listOf(
                networkRepository,
                repositoryModule,
                viewModelModule,
                fragmentModule
            )
        )
    }
}