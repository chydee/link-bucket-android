package com.hoodlums.linkbucket.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(context: Context) {
    companion object {
        // Shared preferences file name
        private const val PREF_NAME = "com.hoodlums.linkbucket.UserPreferences"
        private const val TOKEN = "token"

        private lateinit var preferences: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor
    }

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)!!
        editor = preferences.edit()
    }

    fun setUserToken(token: String) {
        preferences.edit().putString(TOKEN, token).apply()
    }

    fun getUserToken(): String? {
        return preferences.getString(TOKEN, null)
    }


    fun clearAllStoredData() {
        preferences.edit().clear().apply()
    }
}