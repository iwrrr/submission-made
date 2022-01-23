package com.dicoding.made.submission.core.domain.usecase.movie

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.domain.repository.MovieRepository
import com.dicoding.made.submission.core.utils.SortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> =
        movieRepository.searchMovies(query)

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        movieRepository.getMovies()

    override fun getFavoriteMovies(filter: SortType): Flow<Resource<List<Movie>>> =
        movieRepository.getFavoriteMovies(filter)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}