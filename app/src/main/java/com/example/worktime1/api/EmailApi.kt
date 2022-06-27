package com.example.worktime1.api

import com.example.worktime1.model.ConfirmModel
import com.example.worktime1.model.EmailModel
import com.example.worktime1.utils.ApiConstants
import com.example.worktime1.utils.ApiConstants.EMAIL_URL
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface EmailApi {
    @POST(EMAIL_URL)
    fun fetchEmail(@Body data: Map<String, String>): Call<EmailModel>

    @POST(ApiConstants.CONFIRM_URL)
    fun fetchConfirm(@Body data: Map<String, String>): Call<ConfirmModel>
}