package com.example.worktime1.di

import com.example.worktime1.network.*
import com.example.worktime1.repository.AuthRepository
import com.example.worktime1.repository.AuthRepositoryImpl
import com.example.worktime1.ui.AuthViewModel
import com.example.worktime1.utils.PrefsHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get()) }
}

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideTokenAuthenticator(get()) }
    single { provideHeadersInterceptor(get()) }
    single { provideAuthApi(get()) }
    single { PrefsHelper(androidContext()) }
}