package com.dicoding.made.core.domain.repository

import com.dicoding.made.core.common.Resource
import com.dicoding.made.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getAllTvShows(): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}