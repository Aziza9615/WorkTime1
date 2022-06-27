package com.example.worktime1.ui.main

import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.MainEvent
import com.example.worktime1.model.MainModel
import com.example.worktime1.repository.CompanyRepository

class MainViewModel(private val repository: CompanyRepository): BaseViewModel<BaseEvent>() {

    var list: MutableList<MainModel>? = mutableListOf()

    init {
        fetchMain()
    }

    fun fetchMain() {
        loading.value = true
        disposable.add(
            repository.fetchMain()
                .doOnTerminate { loading.value = false }
                .subscribe(
                    { event.value = MainEvent.MainFetched(it) },
                    { message.value = it.message })
        )
    }
}