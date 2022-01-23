package com.dicoding.made.submission.core.domain.repository

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.utils.SortType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun searchMovies(query: String): Flow<Resource<List<Movie>>>

    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(filter: SortType): Flow<Resource<List<Movie>>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}