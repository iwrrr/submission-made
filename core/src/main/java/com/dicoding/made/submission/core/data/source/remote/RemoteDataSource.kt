package com.dicoding.made.submission.core.data.source.remote

import android.util.Log
import com.dicoding.made.submission.commons.other.NetworkState
import com.dicoding.made.submission.core.data.source.remote.network.ApiService
import com.dicoding.made.submission.core.data.source.remote.response.MovieResponse
import com.dicoding.made.submission.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    private val apiKey = "36bab2577e67680bb7e1c554af4ac423"

    fun searchMovies(query: String): Flow<List<MovieResponse>> {
        return flow {
            try {
                val response = apiService.searchMovies(apiKey, query)
                val data = response.data

                if (data.isNotEmpty()) {
                    emit(data)
                }
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMovies(): Flow<NetworkState<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(apiKey)
                val data = response.data

                if (data.isNotEmpty()) {
                    emit(NetworkState.Success(data))
                } else {
                    emit(NetworkState.Empty)
                }
            } catch (e: Exception) {
                emit(NetworkState.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun searchTvShows(query: String): Flow<List<TvShowResponse>> {
        return flow {
            try {
                val response = apiService.searchTvShows(apiKey, query)
                val data = response.data

                if (data.isNotEmpty()) {
                    emit(data)
                }
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTvShows(): Flow<NetworkState<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows(apiKey)
                val data = response.data

                if (data.isNotEmpty()) {
                    emit(NetworkState.Success(data))
                } else {
                    emit(NetworkState.Empty)
                }
            } catch (e: Exception) {
                emit(NetworkState.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}