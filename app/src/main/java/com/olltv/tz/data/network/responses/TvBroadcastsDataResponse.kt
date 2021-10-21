package com.olltv.tz.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TvBroadcastsDataResponse(
    @Json(name = "items_number")
    val itemsNumber: Int,
    @Json(name = "total")
    val total: Long,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "hasMore")
    val hasMore: Long,
    @Json(name = "items")
    val items: List<TvBroadcastResponse>
) {

    @JsonClass(generateAdapter = true)
    class TvBroadcastResponse(
        @Json(name = "id")
        val id: Long,
        @Json(name = "icon")
        val icon: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "channel_name")
        val channelName: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "start")
        val start: String,
        @Json(name = "stop")
        val stop: String
    )
}