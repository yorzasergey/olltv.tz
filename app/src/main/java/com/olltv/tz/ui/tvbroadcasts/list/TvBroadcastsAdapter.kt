package com.olltv.tz.ui.tvbroadcasts.list

import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.olltv.tz.domen.models.TvBroadcast

class TvBroadcastsAdapter(
    private val onClickListener: OnClickListener
): PagingDataAdapter<TvBroadcast, TvBroadcastViewHolder>(TV_BROADCAST_COMPARATOR) {

    interface OnClickListener {
        fun onClickItem(item: TvBroadcast, transitionImageView: ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvBroadcastViewHolder {
        return TvBroadcastViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TvBroadcastViewHolder, position: Int) {
        val reviewsItem = getItem(position)
        reviewsItem?.let { holder.bind(reviewsItem, onClickListener) }
    }

    companion object {

        private val TV_BROADCAST_COMPARATOR = object : DiffUtil.ItemCallback<TvBroadcast>() {
            override fun areItemsTheSame(oldItem: TvBroadcast, newItem: TvBroadcast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvBroadcast, newItem: TvBroadcast): Boolean {
                return oldItem == newItem
            }
        }
    }
}