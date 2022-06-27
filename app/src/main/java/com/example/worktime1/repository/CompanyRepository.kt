package com.example.worktime1.repository

import com.example.worktime1.api.CompanyApi
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.model.MainModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface CompanyRepository {
    fun fetchCompany(): Observable<MutableList<CompanyModel>>
    fun fetchMain() : Observable<MutableList<MainModel>>
}

class CompanyRepositoryImpl(private val api: CompanyApi) : CompanyRepository {

    override fun fetchCompany(): Observable<MutableList<CompanyModel>> {
        return api.fetchCompany()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchMain(): Observable<MutableList<MainModel>> {
        return api.fetchMain()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}