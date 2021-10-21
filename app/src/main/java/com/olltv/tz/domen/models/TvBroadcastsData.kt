package com.olltv.tz.domen.models

data class TvBroadcastsData(
    val itemsNumber: Int,
    val total: Long,
    val offset: Int,
    val hasMore: Long,
    val items: List<TvBroadcast>
)