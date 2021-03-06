package com.example.worktime1.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<EVENT : BaseEvent>() : ViewModel() {
    var message = MutableLiveData<String>()

    val toast: String = "Hello"

    val loading = MutableLiveData<Boolean>()
    val event = MutableLiveData<EVENT>()
}