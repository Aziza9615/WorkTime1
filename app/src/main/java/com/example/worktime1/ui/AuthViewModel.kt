package com.example.worktime1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.ProfileEvent
import com.example.worktime1.model.EmailModel
import com.example.worktime1.network.ResponseResultStatus
import com.example.worktime1.repository.AuthRepository
import com.example.worktime1.utils.PrefsHelper
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
) :
    BaseViewModel<BaseEvent>() {

    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
    }

    fun sendEmail(email: String) {
        viewModelScope.launch {
            repository.sendEmail(
                EmailModel(
                    email = email
                )
            )
        }
    }
}