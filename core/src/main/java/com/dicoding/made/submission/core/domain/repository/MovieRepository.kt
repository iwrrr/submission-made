package com.dicoding.made.submission.core.domain.repository

import com.dicoding.made.submission.core.common.Resource
import com.dicoding.made.submission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}