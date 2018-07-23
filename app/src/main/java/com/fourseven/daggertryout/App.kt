package com.fourseven.daggertryout

import android.app.Application
import android.content.Context
import com.fourseven.daggertryout.data.DataManager
import com.fourseven.daggertryout.di.component.ApplicationComponent
import com.fourseven.daggertryout.di.component.DaggerApplicationComponent
import com.fourseven.daggertryout.di.module.ApplicationModule
import javax.inject.Inject


/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
class App : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var dataManager: DataManager

    companion object {
        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }
}