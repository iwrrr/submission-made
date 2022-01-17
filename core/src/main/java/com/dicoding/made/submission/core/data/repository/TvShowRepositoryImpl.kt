package com.dicoding.made.submission.core.data.repository

import com.dicoding.made.submission.commons.other.NetworkState
import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.data.source.NetworkBoundResource
import com.dicoding.made.submission.core.data.source.local.LocalDataSource
import com.dicoding.made.submission.core.data.source.remote.RemoteDataSource
import com.dicoding.made.submission.core.data.source.remote.response.TvShowResponse
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.domain.repository.TvShowRepository
import com.dicoding.made.submission.core.utils.AppExecutors
import com.dicoding.made.submission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : TvShowRepository {

    override fun searchTvShows(query: String): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> =
                localDataSource.searchTvShows(query).map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flow<NetworkState<List<TvShowResponse>>> =
                remoteDataSource.searchTvShows(query)

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val movieList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShows(movieList)
            }
        }.asFlow()

    override fun getTvShows(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> =
                localDataSource.getTvShows().map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flow<NetworkState<List<TvShowResponse>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList =
                    DataMapper.mapTvShowResponsesToEntities(
                        data
                    )
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()

    override fun getFavoriteTvShows(): Flow<Resource<List<TvShow>>> =
        localDataSource.getFavoriteTvShows()
            .map { Resource.Success(DataMapper.mapTvShowEntitiesToDomain(it)) }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShowEntity, state) }
    }
}