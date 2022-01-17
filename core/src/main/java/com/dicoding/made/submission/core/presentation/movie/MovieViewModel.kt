package com.dicoding.made.submission.core.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.made.submission.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MovieViewModel @Inject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {

    val movies = movieUseCase.getMovies().asLiveData()

    private val queryChannel = Channel<String>()
    val searchResult = queryChannel.receiveAsFlow()
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {
            movieUseCase.searchMovies(it)
        }
        .asLiveData()

    fun setSearchQuery(query: String?) {
        query?.let {
            queryChannel.trySend(it)
        }
    }
}