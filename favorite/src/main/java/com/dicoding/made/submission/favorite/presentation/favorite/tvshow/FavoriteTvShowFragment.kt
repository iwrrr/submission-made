package com.dicoding.made.submission.favorite.presentation.favorite.tvshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.made.submission.commons.other.Resource
import com.dicoding.made.submission.core.domain.model.TvShow
import com.dicoding.made.submission.core.presentation.MovieCatalogueAdapter
import com.dicoding.made.submission.core.presentation.detail.tvshow.DetailTvShowActivity
import com.dicoding.made.submission.core.utils.SortType
import com.dicoding.made.submission.di.FavoriteModuleDependencies
import com.dicoding.made.submission.favorite.R
import com.dicoding.made.submission.favorite.databinding.FragmentFavoriteTvShowBinding
import com.dicoding.made.submission.favorite.di.DaggerFavoriteTvShowComponent
import com.dicoding.made.submission.favorite.utils.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvShowFragment : Fragment(R.layout.fragment_favorite_tv_show) {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteTvShowViewModel by viewModels { factory }

    private val tvShowAdapter: MovieCatalogueAdapter<TvShow> by lazy {
        MovieCatalogueAdapter(::onTvShowClicked, requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteTvShowComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteTvShowBinding.bind(view)

        setFavoriteTvShows()
        sortMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFavoriteTvShows() {
        binding?.rvTvShows?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvShowAdapter
        }

        viewModel.favTvShows.observe(viewLifecycleOwner, { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Loading -> {
                        binding?.progressBar?.isVisible = true
                    }
                    is Resource.Success -> {
                        binding?.progressBar?.isVisible = false
                        binding?.viewEmpty?.root?.isVisible = resource.data.isNullOrEmpty()
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

    private fun sortMenu() {
        val btnSort = binding?.btnSort
        btnSort?.setOnClickListener {
            PopupMenu(requireContext(), btnSort).run {
                menuInflater.inflate(R.menu.filter_menu, menu)
                setOnMenuItemClickListener {
                    viewModel.filter(
                        when (it.itemId) {
                            R.id.name_asc -> SortType.NAME_ASC
                            R.id.name_desc -> SortType.NAME_DESC
                            else -> SortType.RANDOM
                        }
                    )
                    true
                }
                show()
            }
        }
    }
}