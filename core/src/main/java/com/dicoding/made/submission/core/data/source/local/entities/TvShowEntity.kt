package com.dicoding.made.submission.core.data.source.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tv_shows")
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "original_name")
    var originalName: String? = null,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int = 0,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean

) : Parcelable