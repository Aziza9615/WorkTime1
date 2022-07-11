package com.example.worktime1.ui.company

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.network.ResponseResultStatus
import com.example.worktime1.repository.CompanyRepository
import kotlinx.coroutines.launch

class CompanyViewModel(private val repository: CompanyRepository): BaseViewModel<BaseEvent>() {

    var company = MutableLiveData<List<CompanyModel>>()

    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
        fetchCompany()
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun fetchCompany() {
        viewModelScope.launch {
            repository.fetchCompany()
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.ERROR -> {
                            message.value = it.message
                            loading.value = false
                        }
                        ResponseResultStatus.SUCCESS -> {
                            company.value = it.result
                            loading.value = false
                        }
                        ResponseResultStatus.LOADING -> loading.value = true
                    }
                }
        }

    }
}
