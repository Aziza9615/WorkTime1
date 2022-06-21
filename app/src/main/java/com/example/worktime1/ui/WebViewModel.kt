package com.example.worktime1.ui

import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.CompanyEvent
import com.example.worktime1.model.WebModel
import com.example.worktime1.repository.WebRepository

class WebViewModel(private val repository: WebRepository): BaseViewModel<BaseEvent>() {

    init {
        fetchCompany()
    }

    fun fetchCompany() {
        loading.value = true
        disposable.add(
            repository.fetchCompany()
                .doOnTerminate { loading.value = false }
                .subscribe(
                    { event.value = CompanyEvent.CompanyFetched(it) },
                    { message.value = it.message })
        )
    }
}
