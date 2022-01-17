package com.dicoding.made.submission.di

import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun movieUseCase(): MovieUseCase

    fun tvShowUseCase(): TvShowUseCase
}