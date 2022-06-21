package com.example.worktime1.model

import java.io.Serializable

data class ResponseEmailModel(
    var code: Int,
    var email: String,
    var pin_code: String
): Serializable

data class EmailModel(
    var email: String
)