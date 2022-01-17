package com.dicoding.made.submission.core.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.made.submission.commons.other.CommonType
import com.dicoding.made.submission.commons.ui.base.BaseGenericAdapter
import com.dicoding.made.submission.commons.ui.extensions.loadImage
import com.dicoding.made.submission.commons.ui.extensions.toDate
import com.dicoding.made.submission.commons.ui.extensions.toRate
import com.dicoding.made.submission.core.R
import com.dicoding.made.submission.core.databinding.ItemMoviesBinding
import com.dicoding.made.submission.core.domain.model.Movie
import com.dicoding.made.submission.core.domain.model.TvShow

@Suppress("UNCHECKED_CAST")
class MovieAdapter<T>(
    private val onClick: (T) -> Unit,
    private val context: Context
) : BaseGenericAdapter<CommonType>() {

    private val movieType = 0
    private val tvShowType = 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseGenericViewHolder<CommonType> {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return if (viewType == movieType) {
            MovieViewHolder(binding) as BaseGenericViewHolder<CommonType>
        } else {
            TvShowViewHolder(binding) as BaseGenericViewHolder<CommonType>
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position] is Movie) {
            movieType
        } else {
            tvShowType
        }
    }

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) :
        BaseGenericViewHolder<Movie>(binding.root) {

        override fun onBind(data: Movie) {
            binding.apply {
                ivPoster.loadImage(data.posterPath)
                tvTitle.text = data.title
                tvRate.text = data.voteAverage.toRate()
                tvReleaseDate.text =
                    if (data.releaseDate != context.getString(R.string._null)) {
                        data.releaseDate?.toDate()
                    } else {
                        context.getString(R.string._dash)
                    }
            }

            itemView.setOnClickListener {
                onClick(data as T)
            }
        }
    }

    inner class TvShowViewHolder(private val binding: ItemMoviesBinding) :
        BaseGenericViewHolder<TvShow>(binding.root) {

        override fun onBind(data: TvShow) {
            binding.apply {
                ivPoster.loadImage(data.posterPath)
                tvTitle.text = data.name
                tvRate.text = data.voteAverage.toRate()
                tvReleaseDate.text =
                    if (data.firstAirDate != context.getString(R.string._null)) {
                        data.firstAirDate?.toDate()
                    } else {
                        context.getString(R.string._dash)
                    }
            }

            itemView.setOnClickListener {
                onClick(data as T)
            }
        }
    }
}