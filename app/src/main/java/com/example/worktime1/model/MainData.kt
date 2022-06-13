package com.example.worktime1.model

import java.io.Serializable

data class MainData(
    var id: Int? = null,
    var date: Int? = null,
    var being_late: Int? = null,
    var time: Int? = null
) : Serializable