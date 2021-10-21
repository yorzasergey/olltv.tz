package com.olltv.tz.di.module

import com.olltv.tz.data.repositiories.TvBroadcastRepository
import com.olltv.tz.domen.models.ITvBroadcastRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindBroadcastRepository(tvBroadcastRepository: TvBroadcastRepository): ITvBroadcastRepository
}