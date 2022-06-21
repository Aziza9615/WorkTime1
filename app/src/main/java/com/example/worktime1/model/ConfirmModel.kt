package com.example.worktime1.model

import java.io.Serializable

data class ConfirmModelList(
    val code: Int,
    val result: MutableList<ConfirmModel>? = null,
    val message: String? = null
)

data class ConfirmModel(
    var id: Int? = null,
    var number: String? = null
) : Serializable