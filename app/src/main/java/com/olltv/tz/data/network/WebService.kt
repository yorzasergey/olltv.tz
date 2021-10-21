package com.olltv.tz.data.network

import com.olltv.tz.data.network.responses.TvBroadcastsDataResponse
import retrofit2.Response
import retrofit2.http.*

interface WebService {

    @GET("demo")
    suspend fun getTvBroadcasts(
        @Query("serial_number") serialNumber: String,
        @Query("borderId") borderId: Long,
        @Query("direction") direction: Int
    ): Response<TvBroadcastsDataResponse>
}