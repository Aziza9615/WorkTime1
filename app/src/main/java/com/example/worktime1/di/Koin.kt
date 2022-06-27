package com.example.worktime1.di

import com.example.worktime1.network.*
import com.example.worktime1.repository.*
import com.example.worktime1.ui.confirm.ConfirmViewModel
import com.example.worktime1.ui.email.EmailViewModel
import com.example.worktime1.ui.company.CompanyViewModel
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
    viewModel { CompanyViewModel(get()) }
    viewModel { ConfirmViewModel(get()) }
}

val repositoryModule = module {
    factory<EmailRepository> { EmailRepositoryImpl(get(), get()) }
    factory<CompanyRepository> { CompanyRepositoryImpl(get()) }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideTokenAuthenticator(get()) }
    single { provideHeadersInterceptor(get()) }
    single { provideEmailApi(get()) }
    single { provideCompanyApi(get()) }
    single { PrefsHelper(androidContext()) }
}