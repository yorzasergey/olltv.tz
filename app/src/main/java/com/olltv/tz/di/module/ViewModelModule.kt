package com.olltv.tz.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olltv.tz.di.ViewModelFactory
import com.olltv.tz.di.ViewModelKey
import com.olltv.tz.ui.tvbroadcasts.list.TvBroadcastsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TvBroadcastsListViewModel::class)
    abstract fun bindTvBroadcastsListViewModel(viewModel: TvBroadcastsListViewModel): ViewModel
}