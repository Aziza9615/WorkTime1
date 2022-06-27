package com.example.worktime1.model

import java.io.Serializable

data class MainModel(
    var id: Int? = null,
    var day: String,
    var day_of_week: String,
    var arrival_time: String,
    var late: Boolean,
    var user: Int? = null
) : Serializable