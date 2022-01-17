package com.dicoding.made.submission.core.domain.model

import android.os.Parcelable
import com.dicoding.made.submission.commons.other.CommonType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String? = null,
    val originalTitle: String? = null,
    val originalLanguage: String? = null,
    val adult: Boolean? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val video: Boolean? = null,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val isFavorite: Boolean
) : Parcelable, CommonType