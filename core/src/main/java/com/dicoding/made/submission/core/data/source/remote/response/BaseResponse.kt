package com.dicoding.made.submission.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("results")
    val data: List<T>,
)
