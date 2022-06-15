package com.example.worktime1.repository

import com.example.worktime1.api.AuthApi
import com.example.worktime1.model.EmailModel
import com.example.worktime1.utils.PrefsHelper

interface AuthRepository {
    fun sendEmail(emailModel: EmailModel)
}

class AuthRepositoryImpl(private val api: AuthApi, private val preferences: PrefsHelper) :
    AuthRepository {
    override fun sendEmail(emailModel: EmailModel) {
        api.sendEmail(emailModel)
    }
}

