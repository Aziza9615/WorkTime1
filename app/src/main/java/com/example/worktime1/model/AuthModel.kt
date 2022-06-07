package com.example.worktime1.model

import java.io.Serializable

data class AuthModel(
    var id: Int? = null,
    var username: String? = null
) : Serializable