package com.dicoding.made.submission.favorite.presentation.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowUseCase

class FavoriteTvShowViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {

    val favTvShows = tvShowUseCase.getFavoriteTvShows().asLiveData()
}