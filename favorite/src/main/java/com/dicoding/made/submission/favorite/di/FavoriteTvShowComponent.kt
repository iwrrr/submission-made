package com.dicoding.made.submission.favorite.di

import android.content.Context
import com.dicoding.made.submission.di.FavoriteModuleDependencies
import com.dicoding.made.submission.favorite.presentation.favorite.tvshow.FavoriteTvShowFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteTvShowComponent {

    fun inject(fragment: FavoriteTvShowFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteTvShowComponent
    }
}