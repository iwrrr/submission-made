package com.dicoding.made.submission.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    fun getSorteredMoviesQuery(filter: SortType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movies where is_favorite = 1 ")
        when (filter) {
            SortType.NAME_ASC -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            SortType.NAME_DESC -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            SortType.RANDOM -> {
                simpleQuery.append("ORDER BY random()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSorteredTvShowsQuery(filter: SortType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tv_shows where is_favorite = 1 ")
        when (filter) {
            SortType.NAME_ASC -> {
                simpleQuery.append("ORDER BY name ASC")
            }
            SortType.NAME_DESC -> {
                simpleQuery.append("ORDER BY name DESC")
            }
            SortType.RANDOM -> {
                simpleQuery.append("ORDER BY random()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}