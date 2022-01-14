package com.dicoding.made.submission.core.data.repository

import com.dicoding.made.submission.core.common.Resource
import com.dicoding.made.submission.core.data.source.NetworkBoundResource
import com.dicoding.made.submission.core.data.source.local.LocalDataSource
import com.dicoding.made.submission.core.data.source.remote.RemoteDataSource
import com.dicoding.made.submission.core.data.source.remote.network.ApiResponse
import com.dicoding.made.submission.core.data.source.remote.response.TvShowResponse
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: com.dicoding.made.submission.core.common.AppExecutors
) : TvShowRepository {

    override fun getAllTvShows(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> =
                localDataSource.getAllTvShows().map {
                    com.dicoding.made.submission.core.common.DataMapper.mapTvShowEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList =
                    com.dicoding.made.submission.core.common.DataMapper.mapTvShowResponsesToEntities(
                        data
                    )
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()

    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShows()
            .map { com.dicoding.made.submission.core.common.DataMapper.mapTvShowEntitiesToDomain(it) }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowEntity =
            com.dicoding.made.submission.core.common.DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShowEntity) }
    }
}