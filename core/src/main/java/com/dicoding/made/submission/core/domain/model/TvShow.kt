package com.dicoding.made.submission.core.domain.model

import android.os.Parcelable
import com.dicoding.made.submission.commons.other.CommonType
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String? = null,
    val originalName: String? = null,
    val originalLanguage: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val isFavorite: Boolean
) : Parcelable, CommonType