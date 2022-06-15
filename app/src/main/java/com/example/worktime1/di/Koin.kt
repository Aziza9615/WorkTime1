package com.example.worktime1.di

import com.example.worktime1.network.*
import com.example.worktime1.repository.AuthRepository
import com.example.worktime1.repository.AuthRepositoryImpl
import com.example.worktime1.repository.MainRepository
import com.example.worktime1.repository.MainRepositoryImpl
import com.example.worktime1.ui.AuthViewModel
import com.example.worktime1.ui.main.MainFragment
import com.example.worktime1.ui.main.MainViewModel
import com.example.worktime1.utils.PrefsHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { MainFragment() }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<MainRepository> { MainRepositoryImpl(get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideHeadersInterceptor(get()) }
    single { provideAuthApi(get()) }
    single { provideMainApi(get()) }
    single { PrefsHelper(androidContext()) }
}