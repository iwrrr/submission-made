package com.dicoding.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Wrapper<T>(

    @SerializedName("results")
    val data: List<T>,
)
