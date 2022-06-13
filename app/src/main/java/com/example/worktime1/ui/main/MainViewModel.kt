package com.example.worktime1.ui.main

import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.ListEvent
import com.example.worktime1.model.MainData
import com.example.worktime1.repository.MainRepository

class MainViewModel(private val repository: MainRepository): BaseViewModel<BaseEvent>() {

    var data: MutableList<MainData>? = mutableListOf()

    init {
        fetchList()
    }

    fun fetchList() {
        loading.value = true
        disposable.add(
            repository.fetchList()
                .doOnTerminate { loading.value = false }
                .subscribe(
                    { event.value = ListEvent.ListFetched(it) },
                    { message.value = it.message })
        )
    }
}