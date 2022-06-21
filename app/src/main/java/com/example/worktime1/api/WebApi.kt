package com.example.worktime1.api

import com.example.worktime1.model.WebModel
import com.example.worktime1.utils.ApiConstants.WEB_COMPANY
import io.reactivex.Observable
import retrofit2.http.GET

interface WebApi {
    @GET(WEB_COMPANY)
    fun fetchCompany(): Observable<MutableList<WebModel>>
}