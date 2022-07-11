package com.example.worktime1.api

import com.example.worktime1.model.ArriveModel
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.model.MainModel
import com.example.worktime1.utils.ApiConstants
import com.example.worktime1.utils.ApiConstants.COMPANY_URL
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CompanyApi {
    @GET(COMPANY_URL)
    fun fetchCompany(): Call<MutableList<CompanyModel>>

    @GET(ApiConstants.MAIN_COMPANY)
    fun fetchMain(): Call<MutableList<MainModel>>

    @GET(ApiConstants.MAIN_ARRIVE)
    fun fetchArrive(): Call<MutableList<ArriveModel>>
}