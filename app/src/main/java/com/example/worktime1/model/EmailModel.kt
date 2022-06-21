package com.example.worktime1.model

import java.io.Serializable

data class ResponseEmailModel(
    val code: Int,
    val email: String,
    val pin_code: String
): Serializable

data class EmailModel(
    val email: String
)