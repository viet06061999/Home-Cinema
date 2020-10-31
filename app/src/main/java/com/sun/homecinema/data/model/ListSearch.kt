package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName

data class ListSearch(
    @SerializedName("results")
    val response: List<SearchResponse>
)
