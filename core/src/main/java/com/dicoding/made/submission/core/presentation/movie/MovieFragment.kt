package com.dicoding.made.submission.core.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.made.submission.commons.other.CommonType
import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.R
import com.dicoding.made.submission.core.databinding.FragmentMovieBinding
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.presentation.MovieAdapter
import com.dicoding.made.submission.core.presentation.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val viewModel: MovieViewModel by viewModels()

    private val movieAdapter: MovieAdapter<Movie> by lazy {
        MovieAdapter(::onMovieClicked, requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieBinding.bind(view)

        setMovies()

        setupSearchFlow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMovies() {
        binding?.rvMovies?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }

        viewModel.movies.observe(viewLifecycleOwner, { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Loading -> {
                        binding?.progressBar?.isVisible = true
                    }
                    is Resource.Success -> {
                        binding?.progressBar?.isVisible = false
                        movieAdapter.submitList(resource.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.isVisible = false
                    }
                }
            }
        })
    }

    private fun onMovieClicked(movie: Movie) {
        Intent(requireContext(), DetailMovieActivity::class.java).also { intent ->
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
            startActivity(intent)
        }
    }

    private fun setupSearchFlow() {
        binding?.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.setSearchQuery(newText)
                    }
                    return true
                }
            })

            viewModel.searchResult.observe(viewLifecycleOwner, { data ->
                val movies = arrayListOf<Movie>()
                if (data != null) {
                    data.map {
                        movies.add(it)
                    }
                    movieAdapter.submitList(movies as List<CommonType>?)
                }
            })
        }
    }
}