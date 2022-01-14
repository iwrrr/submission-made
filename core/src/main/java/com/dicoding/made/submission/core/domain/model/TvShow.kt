package com.dicoding.made.submission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val originalName: String,
    val originalLanguage: String,
    val posterPath: String,
    val backdropPath: String,
    val firstAirDate: String,
    val overview: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean
) : Parcelable