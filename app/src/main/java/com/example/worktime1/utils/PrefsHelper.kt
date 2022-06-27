package com.example.worktime1.utils

import android.content.Context
import android.content.SharedPreferences

class PrefsHelper(private val context: Context) {

    private val PREFS_NAME = "WorkTimeApp"
    private val TOKEN = "TOKEN"
    private val DATE = "DATE"
    private var prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        instance = this
    }

    fun saveDate(date: String) {
        prefs.edit().putString(DATE, date).apply()
    }

    fun getToken(): String {
        return prefs.getString(TOKEN, "") ?: ""
    }

    companion object {
        lateinit var instance: PrefsHelper
    }
}