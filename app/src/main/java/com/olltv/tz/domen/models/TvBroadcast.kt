package com.olltv.tz.domen.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvBroadcast(
    val id: Long,
    val icon: String,
    val name: String,
    val channelName: String,
    val description: String,
    val startTime: String,
    val stopTime: String
): Parcelable