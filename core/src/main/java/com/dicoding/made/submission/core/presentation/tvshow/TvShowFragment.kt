package com.dicoding.made.submission.core.presentation.tvshow

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
import com.dicoding.made.submission.core.databinding.FragmentTvShowBinding
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.presentation.MovieCatalogueAdapter
import com.dicoding.made.submission.core.presentation.detail.tvshow.DetailTvShowActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private val viewModel: TvShowViewModel by viewModels()

    private val tvShowAdapter: MovieCatalogueAdapter<TvShow> by lazy {
        MovieCatalogueAdapter(::onTvShowClicked, requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTvShowBinding.bind(view)

        setupSearchFlow()

        setTvShows()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setTvShows() {
        binding?.rvTvShows?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvShowAdapter
        }

        viewModel.tvShows.observe(viewLifecycleOwner, { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Loading -> {
                        binding?.progressBar?.isVisible = true
                    }
                    is Resource.Success -> {
                        binding?.progressBar?.isVisible = false
                        tvShowAdapter.submitList(resource.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.isVisible = false
                    }
                }
            }
        })
    }

    private fun onTvShowClicked(tvShow: TvShow) {
        Intent(requireContext(), DetailTvShowActivity::class.java).also { intent ->
            intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShow)
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

            viewModel.searchResult.observe(viewLifecycleOwner, { resource ->
                val tvShows = arrayListOf<TvShow>()

                resource.data?.map {
                    tvShows.add(it)
                }

                if (resource != null) {
                    when (resource) {
                        is Resource.Loading -> {
                            binding?.progressBar?.isVisible = true
                        }
                        is Resource.Success -> {
                            binding?.progressBar?.isVisible = false
                            tvShowAdapter.submitList(tvShows as List<CommonType>)
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.isVisible = false
                        }
                    }
                }
            })
        }
    }
}