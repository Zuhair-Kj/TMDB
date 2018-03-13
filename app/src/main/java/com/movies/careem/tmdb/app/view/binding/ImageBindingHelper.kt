package com.movies.careem.tmdb.app.view.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.movies.careem.tmdb.app.utils.ImageUrlHelper
import com.nostra13.universalimageloader.core.ImageLoader

class ImageBindingHelper {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun bindImageUrl(imageView: ImageView, imageName: String) {
            val finalUrl = ImageUrlHelper.getImageFullUrl(imageView.context, imageName)
            ImageLoader.getInstance()
                    .displayImage(finalUrl, imageView)
        }
    }
}