package com.dicoding.made.submission.core.domain.repository

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun searchTvShows(query: String): Flow<List<TvShow>>

    fun getTvShows(): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShow(): Flow<List<TvShow>>

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}