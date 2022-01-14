package com.dicoding.made.core.data.source.remote.network

import com.dicoding.made.core.data.source.remote.response.MovieResponse
import com.dicoding.made.core.data.source.remote.response.TvShowResponse
import com.dicoding.made.core.data.source.remote.response.Wrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): Wrapper<MovieResponse>

    @GET("tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): Wrapper<TvShowResponse>
}