package com.example.worktime1.api

import com.example.worktime1.model.ConfirmModel
import com.example.worktime1.model.EmailModel
import com.example.worktime1.utils.ApiConstants.CONFIRM_URL
import com.example.worktime1.utils.ApiConstants.EMAIL_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailApi {
    @POST(EMAIL_URL)
    fun fetchEmail(@Body data: Map<String, String>): Call<EmailModel>

    @POST(CONFIRM_URL)
    fun code(@Body data: Map<String, String>): Call<ConfirmModel>}