package com.example.worktime1.api

import com.example.worktime1.model.MainData
import com.example.worktime1.utils.ApiConstants.GET_LIST
import io.reactivex.Observable
import retrofit2.http.GET

interface MainApi {
    @GET(GET_LIST)
    fun fetchList(): Observable<MutableList<MainData>>
}