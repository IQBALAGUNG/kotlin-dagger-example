package com.fourseven.daggertryout.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity