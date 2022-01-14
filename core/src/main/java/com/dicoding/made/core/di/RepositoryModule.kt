package com.dicoding.made.core.di

import com.dicoding.made.core.data.repository.MovieRepositoryImpl
import com.dicoding.made.core.data.repository.TvShowRepositoryImpl
import com.dicoding.made.core.domain.repository.MovieRepository
import com.dicoding.made.core.domain.repository.TvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideTvShowRepository(tvShowRepositoryImpl: TvShowRepositoryImpl): TvShowRepository
}