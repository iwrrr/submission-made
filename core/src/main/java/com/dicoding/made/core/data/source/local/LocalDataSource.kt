package com.dicoding.made.core.data.source.local

import com.dicoding.made.core.data.source.local.entities.MovieEntity
import com.dicoding.made.core.data.source.local.entities.TvShowEntity
import com.dicoding.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> =
        movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> =
        movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) =
        movieDao.insertMovies(movies)

    fun updateFavoriteMovie(movie: MovieEntity) =
        movieDao.updateFavoriteMovie(movie)

    fun getAllTvShows(): Flow<List<TvShowEntity>> =
        movieDao.getAllTvShows()

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> =
        movieDao.getFavoriteTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) =
        movieDao.insertTvShows(tvShows)

    fun updateFavoriteTvShow(tvShow: TvShowEntity) =
        movieDao.updateFavoriteTvShow(tvShow)
}