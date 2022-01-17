package com.dicoding.made.submission.core.domain.usecase.tvshow

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractor @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : TvShowUseCase {

    override fun getAllTourism(): Flow<Resource<List<TvShow>>> =
        tvShowRepository.getTvShows()

    override fun getFavoriteTvShow(): Flow<List<TvShow>> =
        tvShowRepository.getFavoriteTvShow()

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        tvShowRepository.setFavoriteTvShow(tvShow, state)
}