package com.dicoding.made.submission.di

import com.dicoding.made.submission.core.domain.usecase.movie.MovieInteractor
import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowInteractor
import com.dicoding.made.submission.core.domain.usecase.tvshow.TvShowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    @Singleton
    abstract fun provideTvShowUseCase(tvShowInteractor: TvShowInteractor): TvShowUseCase
}