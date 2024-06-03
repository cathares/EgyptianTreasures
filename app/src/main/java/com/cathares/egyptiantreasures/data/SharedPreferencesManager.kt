package com.cathares.egyptiantreasures.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SharedPreferencesManager {
    private const val PREF_NAME = "MySharedPrefs"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveField(key: String, field: List<List<Int>>) {
        val value = Json.encodeToString(field)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getField(key: String, defaultValue: String): List<List<Int>> {
        val serializedField =  sharedPreferences.getString(key, defaultValue) ?: defaultValue
        return Json.decodeFromString(serializedField)
    }
}