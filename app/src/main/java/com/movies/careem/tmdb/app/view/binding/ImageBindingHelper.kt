package com.movies.careem.tmdb.app.view.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.movies.careem.tmdb.app.utils.ImageUrlHelper

class ImageBindingHelper {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImageUrl(imageView: ImageView, imageName: String?) {
            val finalUrl = ImageUrlHelper.getImageFullUrl(imageView.context, imageName?:"")
            Glide.with(imageView.context)
                    .asBitmap()
                    .load(finalUrl)
                    .into(imageView)
        }
    }
}