package com.dicoding.made.submission.core.data.source

import com.dicoding.made.submission.commons.other.NetworkState
import com.dicoding.made.submission.commons.other.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val dbSource = loadFromDB().first()
        val apiResponse = createCall().first()

        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())

            when (apiResponse) {
                is NetworkState.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is NetworkState.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is NetworkState.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): Flow<NetworkState<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}