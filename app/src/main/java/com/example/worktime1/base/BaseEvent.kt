package com.example.worktime1.base

import com.example.worktime1.model.EmailModel
import com.example.worktime1.model.MainData
import com.example.worktime1.model.ResponseEmailModel
import com.example.worktime1.model.WebModel

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class EmailEvent : BaseEvent() {
    class EmailFetched(val item: EmailModel) : EmailEvent()
}

sealed class ListEvent : BaseEvent() {
    class ListFetched(val array: MutableList<MainData>?) : ListEvent()
}
sealed class CompanyEvent : BaseEvent() {
    class CompanyFetched(val array: MutableList<WebModel>?) : CompanyEvent()
}
