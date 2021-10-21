package com.olltv.tz.data.network.datasource

import com.olltv.tz.Resource
import com.olltv.tz.data.network.WebService
import com.olltv.tz.data.network.responses.TvBroadcastsDataResponse
import javax.inject.Inject

class TvBroadcastDataSource @Inject constructor(
    private val service: WebService
) : BaseDataSource() {

    suspend fun getTvBroadcasts(
        serialNumber: String,
        borderId: Long,
        direction: Int
    ): Resource<TvBroadcastsDataResponse> {
       return getResult { service.getTvBroadcasts(serialNumber, borderId, direction) }
    }
}