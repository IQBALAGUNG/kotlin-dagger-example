package com.fourseven.daggertryout.di

import java.lang.annotation.RetentionPolicy
import kotlin.annotation.Retention
import javax.inject.Qualifier

/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext