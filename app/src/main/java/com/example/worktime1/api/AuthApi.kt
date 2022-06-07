package com.example.worktime1.api

import com.example.worktime1.model.AuthModel
import com.example.worktime1.model.TokenModel
import com.example.worktime1.utils.ApiConstants.LOGIN_TOKEN_URL
import com.example.worktime1.utils.ApiConstants.REFRESH_TOKEN_URL
import com.example.worktime1.utils.ApiConstants.REGISTER_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(REGISTER_URL)
    fun regUser(@Body data: AuthModel): Call<AuthModel>

    @POST(LOGIN_TOKEN_URL)
    fun login(@Body data: Map<String, String>): Call<TokenModel>

    @POST(REFRESH_TOKEN_URL)
    fun refreshToken(@Body data: TokenModel): Call<TokenModel>
}