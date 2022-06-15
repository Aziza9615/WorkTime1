package com.example.worktime1.network

import com.example.worktime1.api.AuthApi
import com.example.worktime1.api.MainApi
import com.example.worktime1.model.EmailModel
import com.example.worktime1.utils.ApiConstants.BASE_URL
import com.example.worktime1.utils.PrefsHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOkHttpClient(
    headersInterceptor: HeadersInterceptor,
): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(headersInterceptor)
        .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideAuthApi(retrofit: Retrofit) = retrofit.create(AuthApi::class.java)
fun provideMainApi(retrofit: Retrofit) = retrofit.create(MainApi::class.java)

fun provideHeadersInterceptor(preferences: PrefsHelper)
        = HeadersInterceptor(preferences)

class HeadersInterceptor(private val preferences: PrefsHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences.getToken()
        val request = chain.request().newBuilder()
        if (token.isNotEmpty())
            request.addHeader("token", "$token")

        return chain.proceed(request.build())
    }
}

fun provideAuthWithOutAuthenticatorApi(): AuthApi {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(provideHttpLoginingInterceptor()).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)
}

    private fun countOfFailedResponse(response: Response): Int {
        var count = 1
        while (response.priorResponse != null) {
            count += 1
        }
        return count
    }
