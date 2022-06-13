package com.example.worktime1.base

import com.example.worktime1.model.AuthModel
import com.example.worktime1.model.MainData

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class ProfileEvent : BaseEvent() {
    class UserIsStuffFetched(val item: AuthModel) : ProfileEvent()
}

sealed class ListEvent : BaseEvent() {
    class ListFetched(val array: MutableList<MainData>?) : ListEvent()
}
