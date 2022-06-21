package com.example.worktime1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.network.ResponseResultStatus
import com.example.worktime1.repository.EmailRepository
import kotlinx.coroutines.launch

class EmailViewModel(private val repository: EmailRepository) : BaseViewModel<BaseEvent>() {

    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
    }

    fun email( email: String) {
        viewModelScope.launch {
            repository.email(email)
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.SUCCESS -> {
                            email(email.toString())
                        }
                        ResponseResultStatus.ERROR -> {
                            it.message.let { error.value = it }
                        }
                    }
                }
        }
    }
}
