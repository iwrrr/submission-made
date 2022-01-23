package com.dicoding.made.submission.commons.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.made.submission.commons.ui.recyclerview.BindRecyclerViewHolder

open class BaseGenericAdapter<T> :
    ListAdapter<T, BaseGenericAdapter.BaseGenericViewHolder<T>>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseGenericViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BaseGenericViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseGenericViewHolder<T>, position: Int) {
        holder.onBind(currentList[position])
    }

    open class BaseGenericViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView),
        BindRecyclerViewHolder<T> {

        override fun onBind(data: T) {}
    }
}