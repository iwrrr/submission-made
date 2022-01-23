package com.dicoding.made.submission.core.data.source.local

import com.dicoding.made.submission.core.data.source.local.entities.MovieEntity
import com.dicoding.made.submission.core.data.source.local.entities.TvShowEntity
import com.dicoding.made.submission.core.data.source.local.room.MovieDao
import com.dicoding.made.submission.core.utils.SortType
import com.dicoding.made.submission.core.utils.SortUtils
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

    suspend fun insertMovies(movies: List<MovieEntity>) =
        movieDao.insertMovies(movies)

    suspend fun deleteMovies() = movieDao.deleteMovies()

    fun getFavoriteMovies(filter: SortType): Flow<List<MovieEntity>> {
        val query = SortUtils.getSorteredMoviesQuery(filter)
        return movieDao.getFavoriteMovies(query)
    }

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun searchTvShows(query: String): Flow<List<TvShowEntity>> =
        movieDao.searchTvShows(query)

    fun getTvShows(): Flow<List<TvShowEntity>> =
        movieDao.getTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) =
        movieDao.insertTvShows(tvShows)

    suspend fun deleteTvShows() = movieDao.deleteTvShows()

    fun getFavoriteTvShows(filter: SortType): Flow<List<TvShowEntity>> {
        val query = SortUtils.getSorteredTvShowsQuery(filter)
        return movieDao.getFavoriteTvShows(query)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        movieDao.updateFavoriteTvShow(tvShow)
    }
}