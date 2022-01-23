package com.dicoding.made.submission.favorite.presentation.favorite.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase
import com.dicoding.made.submission.core.utils.SortType

class FavoriteMovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val _filter = MutableLiveData<SortType>()

    val favMovies = _filter.switchMap {
        movieUseCase.getFavoriteMovies(it).asLiveData()
    }

    init {
        _filter.value = SortType.RANDOM
    }

    fun filter(filterType: SortType) {
        _filter.value = filterType
    }
}