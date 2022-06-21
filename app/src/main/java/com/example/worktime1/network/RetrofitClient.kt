package com.example.worktime1.network

import com.example.worktime1.api.ConfirmApi
import com.example.worktime1.api.EmailApi
import com.example.worktime1.api.MainApi
import com.example.worktime1.api.WebApi
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
    tokenAuthenticator: TokenAuthenticator
): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(headersInterceptor)
        .authenticator(tokenAuthenticator)
        .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideTokenAuthenticator(preferences: PrefsHelper) = TokenAuthenticator(preferences)

fun provideEmailApi(retrofit: Retrofit) = retrofit.create(EmailApi::class.java)
fun provideMainApi(retrofit: Retrofit) = retrofit.create(MainApi::class.java)
fun provideWebApi(retrofit: Retrofit) = retrofit.create(WebApi::class.java)
fun provideConfirmApi(retrofit: Retrofit) = retrofit.create(ConfirmApi::class.java)

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

fun provideAuthWithOutAuthenticatorApi(): EmailApi {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(provideHttpLoginingInterceptor()).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EmailApi::class.java)
}

class TokenAuthenticator(
    private val preferences: PrefsHelper
) : Authenticator {

    val api = provideAuthWithOutAuthenticatorApi()
    private val MAX_COUNT_OF_FALL_RESPONSE = 3
    override fun authenticate(route: Route?, response: Response): Request? {
        if (countOfFailedResponse(response) >= MAX_COUNT_OF_FALL_RESPONSE) {
            return null
        }

        return response.request
            .newBuilder()
            .addHeader("Authorization", "Token ${preferences.getToken()}")
            .build()
    }


    private fun countOfFailedResponse(response: Response): Int {
        var count = 1
        while (response.priorResponse != null) {
            count += 1
        }
        return count
    }
}
