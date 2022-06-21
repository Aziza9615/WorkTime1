package com.example.worktime1.repository

import com.example.worktime1.api.WebApi
import com.example.worktime1.model.WebModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface WebRepository {
    fun fetchCompany(): Observable<MutableList<WebModel>>
}

class WebRepositoryImpl(private val api: WebApi) : WebRepository {
    override fun fetchCompany(): Observable<MutableList<WebModel>> {
        return api.fetchCompany()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}


