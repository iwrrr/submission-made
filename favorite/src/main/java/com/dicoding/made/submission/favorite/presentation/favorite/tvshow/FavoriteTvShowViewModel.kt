package com.dicoding.made.submission.favorite.presentation.favorite.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowUseCase
import com.dicoding.made.submission.core.utils.SortType

class FavoriteTvShowViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {

    private val _filter = MutableLiveData<SortType>()

    val favTvShows = _filter.switchMap {
        tvShowUseCase.getFavoriteTvShows(it).asLiveData()
    }

    init {
        _filter.value = SortType.RANDOM
    }

    fun filter(filterType: SortType) {
        _filter.value = filterType
    }
}