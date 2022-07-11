package com.example.worktime1.model

import java.io.Serializable

data class MainModel(
    var id: Int? = null,
    var day_of_week: String,
    var start_work: String,
    var finish_work: String,
    var additional_time: String? = null,
    var parent: Int? = null
) : Serializable