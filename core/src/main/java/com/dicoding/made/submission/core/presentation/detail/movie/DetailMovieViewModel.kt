package com.dicoding.made.submission.core.presentation.detail.movie

import androidx.lifecycle.ViewModel
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, state: Boolean) {
        movieUseCase.setFavoriteMovie(movie, state)
    }
}