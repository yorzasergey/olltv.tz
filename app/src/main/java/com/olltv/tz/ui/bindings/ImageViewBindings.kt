package com.olltv.tz.ui.bindings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.olltv.tz.R
import kotlin.random.Random

/**
 * Set image loaded from url.
 *
 * @param url Image url to download and set as drawable.
 * @param placeholderId Drawable resource identifier to set while downloading image.
 */
@BindingAdapter("imageUrl", "imagePlaceholder", requireAll = false)
fun ImageView.imageUrl(url: String?, @DrawableRes placeholderId: Int?) {
    url?.let {
        if(url.isNotBlank()) {
            val placeHolderDrawable = placeholderId?.let {
                ContextCompat.getDrawable(context, it)
            }

            Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeHolderDrawable)
                .into(this)
        }
    }
}
