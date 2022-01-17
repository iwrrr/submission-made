package com.dicoding.made.submission.favorite.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowUseCase
import com.dicoding.made.submission.favorite.presentation.favorite.movie.FavoriteMovieViewModel
import com.dicoding.made.submission.favorite.presentation.favorite.tvshow.FavoriteTvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(tvShowUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
}