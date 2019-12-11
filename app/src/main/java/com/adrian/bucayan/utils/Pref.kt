package com.adrian.bucayan.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */

class Pref @Inject constructor(context: Context) {

    companion object {
        val KEY_LAST_SEARCH = "last_search"
        val KEY_LAST_COUNTRY = "last_country"
        val KEY_LAST_ISO = "last_country"
        val KEY_LAST_MEDIA = "last_media"
    }

    val PREFS_FILENAME = "pref"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var PREF_LAST_SEARCH: String?
        get() = prefs.getString(KEY_LAST_SEARCH, "")
        set(value) = prefs.edit().putString(KEY_LAST_SEARCH, value).apply()

    var PREF_LAST_COUNTRY: String?
        get() = prefs.getString(KEY_LAST_COUNTRY, "")
        set(value) = prefs.edit().putString(KEY_LAST_COUNTRY, value).apply()

    var PREF_LAST_ISO: String?
        get() = prefs.getString(KEY_LAST_ISO, "")
        set(value) = prefs.edit().putString(KEY_LAST_ISO, value).apply()

    var PREF_LAST_MEDIA: String?
        get() = prefs.getString(KEY_LAST_MEDIA, "")
        set(value) = prefs.edit().putString(KEY_LAST_MEDIA, value).apply()

}