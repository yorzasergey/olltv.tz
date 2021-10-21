package com.olltv.tz.ui.tvbroadcasts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olltv.tz.databinding.ListItemTvBroadcastBinding
import com.olltv.tz.domen.models.TvBroadcast

class TvBroadcastViewHolder(private val binding: ListItemTvBroadcastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TvBroadcast, onClickListener: TvBroadcastsAdapter.OnClickListener) {
        binding.root.setOnClickListener {
            onClickListener.onClickItem(item, binding.ivBroadcastImage)
        }
        binding.ivBroadcastImage.transitionName = item.id.toString()
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): TvBroadcastViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return TvBroadcastViewHolder(
                ListItemTvBroadcastBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }
}