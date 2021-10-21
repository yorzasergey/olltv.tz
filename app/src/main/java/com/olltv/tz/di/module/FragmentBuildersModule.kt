package com.olltv.tz.di.module

import com.olltv.tz.ui.tvbroadcasts.detail.TvBroadcastsDetailFragment
import com.olltv.tz.ui.tvbroadcasts.list.TvBroadcastsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): TvBroadcastsListFragment

    @ContributesAndroidInjector
    abstract fun contributeTvBroadcastsDetailFragment(): TvBroadcastsDetailFragment
}