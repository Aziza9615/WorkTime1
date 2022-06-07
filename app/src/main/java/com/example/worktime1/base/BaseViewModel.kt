package com.example.worktime1.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<EVENT : BaseEvent> : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val event = MutableLiveData<EVENT>()
    val disposable: CompositeDisposable by lazy {
        return@lazy CompositeDisposable()
    }
}