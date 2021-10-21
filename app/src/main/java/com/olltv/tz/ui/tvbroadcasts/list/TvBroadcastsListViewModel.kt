package com.olltv.tz.ui.tvbroadcasts.list

import android.app.Application
import android.provider.Settings
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.olltv.tz.domen.TvBroadcastPagingSource
import com.olltv.tz.domen.models.ITvBroadcastRepository
import javax.inject.Inject


class TvBroadcastsListViewModel @Inject constructor(
    private val application: Application,
    private val tvBroadcastRepository: ITvBroadcastRepository
) : ViewModel() {

    val result = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            val deviceId = Settings.Secure.getString(
                application.baseContext.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            TvBroadcastPagingSource(tvBroadcastRepository, deviceId)
        }
    ).flow.cachedIn(viewModelScope)

}