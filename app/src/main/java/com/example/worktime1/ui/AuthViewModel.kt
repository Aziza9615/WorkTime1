package com.example.worktime1.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.model.AuthModel
import com.example.worktime1.network.ResponseResultStatus
import com.example.worktime1.repository.AuthRepository
import com.example.worktime1.utils.PrefsHelper
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
    private val preferences: PrefsHelper
) :
    BaseViewModel<BaseEvent>() {

    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
    }

    fun regUser(user: AuthModel) {
        viewModelScope.launch {
            repository.regUser(user)
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.SUCCESS -> {
                            login(user.email.toString())
                        }
                        ResponseResultStatus.ERROR -> {
                            message.value = it.message
                        }
                    }
                }
        }
    }

    fun login(email: String) {
        viewModelScope.launch {
            repository.login(email)
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.SUCCESS -> {
                            preferences.saveAccessToken(it?.result?.accessToken)
                            preferences.saveRefreshToken(it?.result?.refreshToken)
                        }
                        ResponseResultStatus.ERROR -> {
                            it.message.let { error.value = it }
                        }
                    }
                }
        }
    }
}