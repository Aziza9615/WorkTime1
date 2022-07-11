package com.example.worktime1.model

data class ArriveModel (
    var id: Int? = null,
    var day: String? = null,
    var day_of_week: String? = null,
    var arrival_time: String,
    var late : Boolean = true,
    var user: Int
)