package com.example.worktime1.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthModel(
    var id: Int? = null,
    var email: String? = null,
    @SerializedName("is_staff")
    var isStuff: Boolean? = null
) : Serializable