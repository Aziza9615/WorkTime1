package com.example.worktime1.model

import java.io.Serializable

data class ConfirmModel(
    val id: Int,
    var email: String,
    var pin_code: String
) : Serializable