package com.dicoding.made.submission.core.domain.usecase.movie

import com.dicoding.made.submission.core.common.Resource
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {

    override fun getAllTourism(): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovies()

    override fun getFavoriteMovie(): Flow<List<Movie>> =
        movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}