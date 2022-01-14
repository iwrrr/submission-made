package com.dicoding.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val adult: Boolean,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val genreIds: List<Int>,
    val overview: String,
    val popularity: Double,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean
) : Parcelable