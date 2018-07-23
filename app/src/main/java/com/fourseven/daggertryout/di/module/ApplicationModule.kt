package com.fourseven.daggertryout.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.fourseven.daggertryout.di.ApplicationContext
import com.fourseven.daggertryout.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull


/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return "demo-dagger.db"
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseVersion(): Int {
        return 2
    }

    @Provides
    internal fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)
    }
}
