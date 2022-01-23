package com.dicoding.made.submission.commons.ui.recyclerview

interface BindRecyclerViewHolder<T> {

    fun onBind(data: T)
}