package com.fourseven.daggertryout.di.component

import com.fourseven.daggertryout.MainActivity
import com.fourseven.daggertryout.di.PerActivity
import com.fourseven.daggertryout.di.module.ActivityModule
import dagger.Component


/**
 * Created by Iqbal Agung Gunawan on 21/07/2018.
 */
@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}