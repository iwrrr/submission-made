package com.dicoding.made.submission.core.common

import com.dicoding.made.submission.core.data.source.local.entities.MovieEntity
import com.dicoding.made.submission.core.data.source.local.entities.TvShowEntity
import com.dicoding.made.submission.core.data.source.remote.response.MovieResponse
import com.dicoding.made.submission.core.data.source.remote.response.TvShowResponse
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.domain.model.TvShow

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                adult = it.adult,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                popularity = it.popularity,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                adult = it.adult,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                popularity = it.popularity,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieDomainToEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            originalTitle = input.originalTitle,
            originalLanguage = input.originalLanguage,
            adult = input.adult,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            overview = input.overview,
            popularity = input.popularity,
            video = input.video,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                id = it.id,
                name = it.name,
                originalName = it.originalName,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                name = it.name,
                originalName = it.originalName,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapTvShowDomainToEntity(input: TvShow) =
        TvShowEntity(
            id = input.id,
            name = input.name,
            originalName = input.originalName,
            originalLanguage = input.originalLanguage,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            firstAirDate = input.firstAirDate,
            overview = input.overview,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
}