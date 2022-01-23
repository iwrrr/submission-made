package com.dicoding.made.submission.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.made.submission.core.data.source.local.room.MovieDao
import com.dicoding.made.submission.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("tmdb".toCharArray())
        val factory = SupportFactory(passPhrase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao =
        database.movieDao()
}