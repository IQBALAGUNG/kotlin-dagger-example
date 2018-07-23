package com.fourseven.daggertryout.di.component

import android.app.Application
import android.content.Context
import com.fourseven.daggertryout.App
import com.fourseven.daggertryout.data.DBHelper
import com.fourseven.daggertryout.data.DataManager
import com.fourseven.daggertryout.data.SharedPrefsHelper
import com.fourseven.daggertryout.di.ApplicationContext
import com.fourseven.daggertryout.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    @get:ApplicationContext
    val context: Context

    val application: Application

    val dataManager: DataManager

    val preferenceHelper: SharedPrefsHelper

    val dbHelper: DBHelper

    fun inject(app: App)

}