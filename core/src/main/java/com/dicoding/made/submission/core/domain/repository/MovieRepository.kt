package com.dicoding.made.submission.core.domain.repository

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun searchMovies(query: String): Flow<List<Movie>>

    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}