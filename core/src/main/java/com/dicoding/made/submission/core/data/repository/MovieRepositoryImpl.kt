package com.dicoding.made.submission.core.data.repository

import com.dicoding.made.submission.commons.other.NetworkState
import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.data.source.NetworkBoundResource
import com.dicoding.made.submission.core.data.source.local.LocalDataSource
import com.dicoding.made.submission.core.data.source.remote.RemoteDataSource
import com.dicoding.made.submission.core.data.source.remote.response.MovieResponse
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.domain.repository.MovieRepository
import com.dicoding.made.submission.core.utils.AppExecutors
import com.dicoding.made.submission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepository {

    override fun searchMovies(query: String): Flow<List<Movie>> {
        return remoteDataSource.searchMovies(query).map { DataMapper.mapMovieResponsesToDomain(it) }
    }

    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flow<NetworkState<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}