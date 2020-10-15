package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    var movies: List<MovieListResponse>
)
