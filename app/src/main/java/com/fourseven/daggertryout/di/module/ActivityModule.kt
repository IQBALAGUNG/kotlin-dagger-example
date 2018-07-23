package com.fourseven.daggertryout.di.module

import android.app.Activity
import android.content.Context
import com.fourseven.daggertryout.di.ActivityContext
import dagger.Module
import dagger.Provides


/**
 * Created by Iqbal Agung Gunawan on 21/07/2018.
 */
@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }
}
