package com.olltv.tz.domen.models

import com.olltv.tz.Resource

interface ITvBroadcastRepository {

    suspend fun getTvBroadcasts(
        serialNumber: String,
        borderId: Long,
        direction: Int
    ): Resource<TvBroadcastsData>
}