package com.example.worktime1.api

import com.example.worktime1.model.CompanyModel
import com.example.worktime1.model.MainModel
import com.example.worktime1.utils.ApiConstants
import com.example.worktime1.utils.ApiConstants.COMPANY_URL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyApi {
    @GET(COMPANY_URL)
    fun fetchCompany(): Observable<MutableList<CompanyModel>>

    @GET(ApiConstants.MAIN_COMPANY)
    fun fetchMain(): Observable<MutableList<MainModel>>
}