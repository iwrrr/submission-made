package com.dicoding.made.core.data.source.remote.network

import com.dicoding.made.core.data.source.remote.response.MovieResponse
import com.dicoding.made.core.data.source.remote.response.TvShowResponse
import com.dicoding.made.core.data.source.remote.response.Wrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): Wrapper<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Wrapper<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String
    ): Wrapper<MovieResponse>

    @GET("tv/airing_today")
    suspend fun getTvAiringToday(
        @Query("api_key") apiKey: String
    ): Wrapper<TvShowResponse>

    @GET("tv/on_the_air")
    suspend fun getTvOnTheAir(
        @Query("api_key") apiKey: String
    ): Wrapper<TvShowResponse>

    @GET("tv/popular")
    suspend fun getTvPopular(
        @Query("api_key") apiKey: String
    ): Wrapper<TvShowResponse>
}