package com.example.worktime1.model

import java.io.Serializable

data class WebModel(
    var id: Int,
    var day: String,
    var day_of_week: String,
    var arrival_time: String,
    var late: Boolean,

) : Serializable