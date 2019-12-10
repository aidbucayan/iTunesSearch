package com.adrian.bucayan.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */

class Pref @Inject constructor(context: Context) {

    companion object {
        val KEY_IS_REMEMBER_ME = "is_remember_me"
        val KEY_USER = "user"
        val KEY_PASSWORD = "password"
        val KEY_COOKIE = "cookie"
    }

    val PREFS_FILENAME = "unisa-pref"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var IS_REMEMBER_ME: Boolean
        get() = prefs.getBoolean(KEY_IS_REMEMBER_ME, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_REMEMBER_ME, value).apply()

    var PREF_USER: String
        get() = prefs.getString(KEY_USER, "")
        set(value) = prefs.edit().putString(KEY_USER, value).apply()

    var PREF_PASSWORD: String
        get() = prefs.getString(KEY_PASSWORD, "")
        set(value) = prefs.edit().putString(KEY_PASSWORD, value).apply()

    var PREF_COOKIE: String
        get() = prefs.getString(KEY_COOKIE, "")
        set(value) = prefs.edit().putString(KEY_COOKIE, value).apply()

}