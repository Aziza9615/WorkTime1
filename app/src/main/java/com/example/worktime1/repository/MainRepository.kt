package com.example.worktime1.repository

import com.example.worktime1.api.MainApi
import com.example.worktime1.model.MainData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface MainRepository {
    fun fetchList(): Observable<MutableList<MainData>>
}

class MainRepositoryImpl(private val api: MainApi) : MainRepository {

    override fun fetchList(): Observable<MutableList<MainData>> {
        return api.fetchList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}