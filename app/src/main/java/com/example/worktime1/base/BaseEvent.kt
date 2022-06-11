package com.example.worktime1.base

import com.example.worktime1.model.AuthModel

sealed class BaseEvent {
    class Error(message: String) : BaseEvent()
    class Success<T>(result: T) : BaseEvent()
    class Loading(state: Boolean) : BaseEvent()
}

sealed class ProfileEvent : BaseEvent() {
    class UserProfileFetched(val item: AuthModel?) : ProfileEvent()
    class UserIsStuffFetched(val item: AuthModel) : ProfileEvent()
}
