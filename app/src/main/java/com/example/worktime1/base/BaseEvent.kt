package com.example.worktime1.base

import com.example.worktime1.model.*

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class EmailEvent : BaseEvent() {
    class EmailFetched(val item: EmailModel) : EmailEvent()
}

sealed class MainEvent : BaseEvent() {
    class MainFetched(val array: MutableList<MainModel>?) : MainEvent()
}

sealed class CompanyEvent : BaseEvent() {
    class CompanyFetched(val array: MutableList<CompanyModel>?) : CompanyEvent()
}

sealed class CodeEvent : BaseEvent() {
    class CodeFetched(val item: ConfirmModel): CodeEvent()
}

sealed class ArriveEvent : BaseEvent() {
    class ArriveFetched(val item: ArriveModel): ArriveEvent()
}
