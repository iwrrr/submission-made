package com.dicoding.made.submission.core.presentation.detail.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        tvShowUseCase.setFavoriteTvShow(tvShow, state)
    }
}