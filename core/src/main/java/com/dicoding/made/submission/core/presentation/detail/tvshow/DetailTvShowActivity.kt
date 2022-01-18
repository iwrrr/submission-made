package com.dicoding.made.submission.core.presentation.detail.tvshow

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.made.submission.commons.ui.extensions.loadImage
import com.dicoding.made.submission.commons.ui.extensions.toDate
import com.dicoding.made.submission.commons.ui.extensions.toRate
import com.dicoding.made.submission.core.R
import com.dicoding.made.submission.core.databinding.ActivityDetailBinding
import com.dicoding.made.submission.core.domain.model.TvShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailTvShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val tvShow = intent.getParcelableExtra<TvShow>(EXTRA_TVSHOW)
        tvShow?.let {
            populateData(it)
        }

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun populateData(tvShow: TvShow?) {
        binding?.apply {
            tvShow?.let {
                ivBackdrop.loadImage(tvShow.backdropPath)
                ivPoster.loadImage(tvShow.posterPath)
                tvRate.text = tvShow.voteAverage.toRate()
                tvTitle.text = tvShow.name
                tvPopularity.text = getString(R.string.popularity, tvShow.popularity.toString())
                tvVoteCount.text = getString(R.string.vote_count, tvShow.voteCount.toString())
                tvOverview.text = tvShow.overview
                tvReleaseDate.text =
                    if (tvShow.firstAirDate != getString(R.string._null)) {
                        tvShow.firstAirDate?.toDate()
                    } else {
                        getString(R.string._dash)
                    }
            }
        }

        tvShow?.let { setFavorite(it) }
    }

    private fun setFavorite(tvShow: TvShow) {
        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        val bounceInterpolator = BounceInterpolator()

        scaleAnimation.duration = 500
        scaleAnimation.interpolator = bounceInterpolator

        var isFavorite = tvShow.isFavorite

        binding?.apply {
            toggleFavorite.setOnClickListener {
                it.startAnimation(scaleAnimation)
                isFavorite = !isFavorite
                if (isFavorite) {
                    viewModel.setFavoriteTvShow(tvShow, isFavorite)
                } else {
                    viewModel.setFavoriteTvShow(tvShow, isFavorite)
                }
            }

            toggleFavorite.isChecked = isFavorite
        }
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_data"
    }
}