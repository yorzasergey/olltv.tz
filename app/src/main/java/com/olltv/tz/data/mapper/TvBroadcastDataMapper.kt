package com.olltv.tz.data.mapper

import com.olltv.tz.data.network.responses.TvBroadcastsDataResponse
import com.olltv.tz.domen.models.TvBroadcast
import com.olltv.tz.domen.models.TvBroadcastsData

fun TvBroadcastsDataResponse.mapToTvBroadcastsData() = TvBroadcastsData(
    itemsNumber = itemsNumber,
    total = total,
    offset = offset,
    hasMore = hasMore,
    items = items.map { it.mapToTvBroadcast() }
)

fun TvBroadcastsDataResponse.TvBroadcastResponse.mapToTvBroadcast() = TvBroadcast(
    id = id,
    icon = icon,
    name = name,
    channelName = channelName,
    description = description,
    startTime = start,
    stopTime = stop
)