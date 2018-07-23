package com.fourseven.daggertryout.data

import android.content.Context
import android.content.res.Resources
import com.fourseven.daggertryout.data.model.User
import com.fourseven.daggertryout.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
@Singleton
class DataManager @Inject constructor(@ApplicationContext context: Context, dbHelper: DBHelper, sharedPrefsHelper: SharedPrefsHelper) {

    var mContext: Context = context
    var mDBHelper: DBHelper = dbHelper
    var mSharedPrefsHelper: SharedPrefsHelper = sharedPrefsHelper

    fun saveAccessToken(accessToken: String) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken(): String? {
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null)
    }

    @Throws(Exception::class)
    fun createUser(user: User): Long {
        return mDBHelper.insertUser(user)
    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long): User {
        return mDBHelper.getUser(userId)
    }
}