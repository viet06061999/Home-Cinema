package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName

data class MovieActorResponse(
    @SerializedName("cast")
    var movies: List<MovieResponse>
)
