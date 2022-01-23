package com.dicoding.made.submission.core.domain.usecase.tvshow

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.domain.repository.TvShowRepository
import com.dicoding.made.submission.core.utils.SortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractor @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : TvShowUseCase {

    override fun searchTvShows(query: String): Flow<Resource<List<TvShow>>> =
        tvShowRepository.searchTvShows(query)

    override fun getTvShows(): Flow<Resource<List<TvShow>>> =
        tvShowRepository.getTvShows()

    override fun getFavoriteTvShows(filter: SortType): Flow<Resource<List<TvShow>>> =
        tvShowRepository.getFavoriteTvShows(filter)

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        tvShowRepository.setFavoriteTvShow(tvShow, state)
}