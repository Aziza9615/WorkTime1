package com.example.worktime1.ui.confirm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.CodeEvent
import com.example.worktime1.base.MainEvent
import com.example.worktime1.model.ConfirmModel
import com.example.worktime1.network.ResponseResultStatus
import com.example.worktime1.repository.EmailRepository
import kotlinx.coroutines.launch

class ConfirmViewModel(private val repository: EmailRepository) : BaseViewModel<BaseEvent>() {

    val error = MutableLiveData<String>()

    init {
        message = MutableLiveData()
    }

    fun code(pin_code: String) {
        viewModelScope.launch {
            repository.code(pin_code)
                .observeForever {
                    when (it.status) {
                        ResponseResultStatus.SUCCESS -> {
                            code(pin_code.toString())
                        }
                        ResponseResultStatus.ERROR -> {
                            it.message.let { error.value = it }
                        }
                    }
                }
        }
    }
}