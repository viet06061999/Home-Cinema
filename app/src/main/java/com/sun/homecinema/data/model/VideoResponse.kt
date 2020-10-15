package com.sun.homecinema.data.model

data class VideoResponse(val results: List<Video?>) {

    fun getTrailer(): Video =
        results.first {
            it?.type == TYPE_TRAILER
        } ?: Video()

    companion object {
        const val TYPE_TRAILER = "Trailer"
    }
}
