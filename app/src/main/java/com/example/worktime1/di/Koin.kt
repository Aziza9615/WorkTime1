package com.example.worktime1.di

import com.example.worktime1.network.*
import com.example.worktime1.repository.*
import com.example.worktime1.ui.EmailViewModel
import com.example.worktime1.ui.WebViewModel
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
    viewModel { EmailViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { WebViewModel(get()) }
}

val repositoryModule = module {
    factory<EmailRepository> { EmailRepositoryImpl(get(), get()) }
    factory<MainRepository> { MainRepositoryImpl(get()) }
    factory<WebRepository> {WebRepositoryImpl(get())}
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideTokenAuthenticator(get()) }
    single { provideHeadersInterceptor(get()) }
    single { provideEmailApi(get()) }
    single { provideMainApi(get()) }
    single { provideWebApi(get()) }
    single { provideConfirmApi(get()) }
    single { PrefsHelper(androidContext()) }
}