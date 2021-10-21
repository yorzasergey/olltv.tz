package com.olltv.tz.data.repositiories

import com.olltv.tz.Resource
import com.olltv.tz.data.mapper.mapToTvBroadcastsData
import com.olltv.tz.data.network.datasource.TvBroadcastDataSource
import com.olltv.tz.domen.models.ITvBroadcastRepository
import com.olltv.tz.domen.models.TvBroadcastsData
import com.olltv.tz.utils.suspendNetworkOperation
import javax.inject.Inject

class TvBroadcastRepository @Inject constructor(
    private val tvBroadcastDataSource: TvBroadcastDataSource
): ITvBroadcastRepository {

    override suspend fun getTvBroadcasts(
        serialNumber: String,
        borderId: Long,
        direction: Int
    ): Resource<TvBroadcastsData> = suspendNetworkOperation(
        networkCall = { tvBroadcastDataSource.getTvBroadcasts(serialNumber, borderId, 1) },
        mapper = { it.mapToTvBroadcastsData()}
    )
}