package com.dicoding.made.submission.core.presentation.detail.movie

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
import com.dicoding.made.submission.core.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        movie?.let {
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

    private fun populateData(movie: Movie) {
        binding?.apply {
            ivBackdrop.loadImage(movie.backdropPath)
            ivPoster.loadImage(movie.posterPath)
            tvRate.text = movie.voteAverage.toRate()
            tvTitle.text = movie.title
            tvPopularity.text = getString(R.string.popularity, movie.popularity.toString())
            tvVoteCount.text = getString(R.string.vote_count, movie.voteCount.toString())
            tvOverview.text = movie.overview
            tvReleaseDate.text =
                if (movie.releaseDate != getString(R.string._null)) {
                    movie.releaseDate?.toDate()
                } else {
                    getString(R.string._dash)
                }
        }

        setFavorite(movie)
    }

    private fun setFavorite(movie: Movie) {
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

        var isFavorite = movie.isFavorite

        binding?.apply {
            toggleFavorite.setOnClickListener {
                it.startAnimation(scaleAnimation)
                isFavorite = !isFavorite
                if (isFavorite) {
                    viewModel.setFavoriteMovie(movie, isFavorite)
                } else {
                    viewModel.setFavoriteMovie(movie, isFavorite)
                }
            }

            toggleFavorite.isChecked = isFavorite
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_data"
    }
}