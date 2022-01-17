package com.dicoding.made.submission.core.data.source.remote.network

import com.dicoding.made.submission.core.data.source.remote.response.MovieResponse
import com.dicoding.made.submission.core.data.source.remote.response.TvShowResponse
import com.dicoding.made.submission.core.data.source.remote.response.Wrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Wrapper<MovieResponse>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): Wrapper<MovieResponse>

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): Wrapper<TvShowResponse>

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Wrapper<TvShowResponse>
}