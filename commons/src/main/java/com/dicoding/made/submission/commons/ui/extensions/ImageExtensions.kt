package com.dicoding.made.submission.commons.ui.extensions

import androidx.constraintlayout.utils.widget.ImageFilterView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.made.submission.commons.R
import com.dicoding.made.submission.commons.utils.Constants.IMG_URL
import com.google.android.material.imageview.ShapeableImageView


fun ShapeableImageView.loadImage(image: String?) {
    Glide.with(this.context)
        .load(IMG_URL + image)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.ic_error)
                .error(R.drawable.ic_error)
        )
        .into(this)
}

fun ImageFilterView.loadImage(image: String?) {
    Glide.with(this.context)
        .load(IMG_URL + image)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.ic_error)
                .error(R.drawable.ic_error)
        )
        .into(this)
}