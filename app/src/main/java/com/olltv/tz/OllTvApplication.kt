package com.olltv.tz

import com.olltv.tz.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class OllTvApplication : DaggerApplication() {

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}