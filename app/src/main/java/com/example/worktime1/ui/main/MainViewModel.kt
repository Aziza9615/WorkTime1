package com.example.worktime1.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.MainEvent
import com.example.worktime1.model.ArriveModel
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.model.MainModel
import com.example.worktime1.network.ResponseResultStatus
import com.example.worktime1.repository.CompanyRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CompanyRepository): BaseViewModel<BaseEvent>() {

    var list = MutableLiveData<List<MainModel>>()

    var arrive = MutableLiveData<List<ArriveModel>>()

    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
        fetchMain()
        fetchArrive()
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun fetchMain() {
        viewModelScope.launch {
            repository.fetchMain()
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.ERROR -> {
                            message.value = it.message
                            loading.value = false
                        }
                        ResponseResultStatus.SUCCESS -> {
                            list.value = it.result
                            loading.value = false
                        }
                        ResponseResultStatus.LOADING -> loading.value = true
                    }
                }
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun fetchArrive() {
        viewModelScope.launch {
            repository.fetchArrive()
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.ERROR -> {
                            message.value = it.message
                            loading.value = false
                        }
                        ResponseResultStatus.SUCCESS -> {
                            arrive.value = it.result
                            loading.value = false
                        }
                        ResponseResultStatus.LOADING -> loading.value = true
                    }
                }
        }

    }
}