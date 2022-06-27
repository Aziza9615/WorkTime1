package com.example.worktime1.ui.company

import androidx.lifecycle.MutableLiveData
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.CompanyEvent
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.repository.CompanyRepository

class CompanyViewModel(private val repository: CompanyRepository): BaseViewModel<BaseEvent>() {

    val data: MutableLiveData<MutableList<CompanyModel>> = MutableLiveData()

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
