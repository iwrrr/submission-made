package com.dicoding.made.submission.core.data.source.local.room

import androidx.room.*
import com.dicoding.made.submission.core.data.source.local.entities.MovieEntity
import com.dicoding.made.submission.core.data.source.local.entities.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE title LIKE :query || '%' OR original_title LIKE :query || '%'")
    fun searchMovies(query: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where is_favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_shows WHERE name LIKE :query || '%' OR original_name LIKE :query || '%'")
    fun searchTvShows(query: String): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows where is_favorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateFavoriteTvShow(tvShow: TvShowEntity)
}