package com.fourseven.daggertryout.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
@Singleton
class SharedPrefsHelper @Inject constructor(sharedPreferences: SharedPreferences) {

    companion object {
        var PREF_KEY_ACCESS_TOKEN = "access-token"
    }

    private var mSharedPreference: SharedPreferences = sharedPreferences

    fun put(key: String, value: String) {
        mSharedPreference.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mSharedPreference.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        mSharedPreference.edit().putBoolean(key, value).apply()
    }

    fun get(key: String, defaultValue: String?): String {
        return mSharedPreference.getString(key, defaultValue)
    }

    fun get(key: String, defaultValue: Int): Int {
        return mSharedPreference.getInt(key, defaultValue)
    }

    fun get(key: String, defaultValue: Boolean): Boolean {
        return mSharedPreference.getBoolean(key, defaultValue)
    }

    fun deleteSavedData(key: String) {
        mSharedPreference.edit().remove(key).apply()
    }
}