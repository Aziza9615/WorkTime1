package com.example.worktime1.network

import com.example.worktime1.api.CompanyApi
import com.example.worktime1.api.EmailApi
import com.example.worktime1.utils.ApiConstants.BASE_URL
import com.example.worktime1.utils.PrefsHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val okHttpClient: OkHttpClient) {

    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideCompanyApi(retrofit: RetrofitClient) = retrofit.provideRetrofit().create(CompanyApi::class.java)
fun provideEmailApi(retrofit: RetrofitClient) = retrofit.provideRetrofit().create(EmailApi::class.java)

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
