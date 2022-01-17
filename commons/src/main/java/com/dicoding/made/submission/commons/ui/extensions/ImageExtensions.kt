package com.dicoding.made.submission.commons.ui.extensions

import androidx.constraintlayout.utils.widget.ImageFilterView
import com.bumptech.glide.Glide
import com.dicoding.made.submission.commons.ui.utils.Constants.IMG_URL
import com.google.android.material.imageview.ShapeableImageView


fun ShapeableImageView.loadImage(image: String?) {
    Glide.with(this.context)
        .load(IMG_URL + image)
        .into(this)
}

fun ImageFilterView.loadImage(image: String?) {
    Glide.with(this.context)
        .load(IMG_URL + image)
        .into(this)
}