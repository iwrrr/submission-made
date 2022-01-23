package com.dicoding.made.submission.favorite.presentation.favorite

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.dicoding.made.submission.favorite.R
import com.dicoding.made.submission.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.made.submission.favorite.presentation.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager, lifecycle)

        val viewPager = binding?.viewPager
        val tabs = binding?.tabs

        viewPager?.adapter = sectionPagerAdapter
        if (tabs != null && viewPager != null) {
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_movies,
            R.string.tab_tv_shows
        )
    }
}