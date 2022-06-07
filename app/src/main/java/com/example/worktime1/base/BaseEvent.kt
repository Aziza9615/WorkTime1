package com.example.worktime1.base

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class LangEvent : BaseEvent() {
    class LangFetched() : LangEvent()
}

sealed class CurrencyEvent : BaseEvent() {
    class CurrencyFetched(it: Any?) : CurrencyEvent()
}

sealed class CategoryEvent : BaseEvent() {
    class CategoryFetched(it: Any?) : CategoryEvent()
}