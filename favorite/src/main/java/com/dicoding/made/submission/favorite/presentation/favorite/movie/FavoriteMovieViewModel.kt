package com.dicoding.made.submission.favorite.presentation.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase

class FavoriteMovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favMovies = movieUseCase.getFavoriteMovies().asLiveData()
}