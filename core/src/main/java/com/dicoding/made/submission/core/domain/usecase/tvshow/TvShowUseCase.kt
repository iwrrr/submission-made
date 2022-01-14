package com.dicoding.made.submission.core.domain.usecase.tvshow

import com.dicoding.made.submission.core.common.Resource
import com.dicoding.made.submission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {

    fun getAllTourism(): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}