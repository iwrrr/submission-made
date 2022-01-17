package com.dicoding.made.submission.core.data.source.local

import com.dicoding.made.submission.core.data.source.local.entities.MovieEntity
import com.dicoding.made.submission.core.data.source.local.entities.TvShowEntity
import com.dicoding.made.submission.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun searchMovies(query: String): Flow<List<MovieEntity>> =
        movieDao.searchMovies(query)

    fun getMovies(): Flow<List<MovieEntity>> =
        movieDao.getMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> =
        movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) =
        movieDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun searchTvShows(query: String): Flow<List<TvShowEntity>> =
        movieDao.searchTvShows(query)

    fun getTvShows(): Flow<List<TvShowEntity>> =
        movieDao.getTvShows()

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> =
        movieDao.getFavoriteTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) =
        movieDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        movieDao.updateFavoriteTvShow(tvShow)
    }
}