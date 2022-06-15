package com.example.worktime1.api

import com.example.worktime1.model.EmailModel
import com.example.worktime1.utils.ApiConstants.LOGIN_TOKEN_URL
import com.example.worktime1.utils.ApiConstants.EMAIL_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(EMAIL_URL)
    fun sendEmail(@Body data: EmailModel): Call<EmailModel>
}