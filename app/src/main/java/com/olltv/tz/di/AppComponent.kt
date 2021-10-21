package com.olltv.tz.di

import android.app.Application
import com.olltv.tz.OllTvApplication
import com.olltv.tz.di.module.ActivityModule
import com.olltv.tz.di.module.AppModule
import com.olltv.tz.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent : AndroidInjector<OllTvApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: OllTvApplication)
}