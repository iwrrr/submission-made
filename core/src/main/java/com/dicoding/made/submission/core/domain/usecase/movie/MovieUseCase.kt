package com.dicoding.made.submission.core.domain.usecase.movie

import com.dicoding.made.submission.core.common.Resource
import com.dicoding.made.submission.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getAllTourism(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}