package com.dicoding.made.submission.core.domain.repository

import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.utils.SortType
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun searchTvShows(query: String): Flow<Resource<List<TvShow>>>

    fun getTvShows(): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShows(filter: SortType): Flow<Resource<List<TvShow>>>

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}