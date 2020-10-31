package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("poster_path")
    val poster: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profile: String? = null
)  : GeneraEntity {

    override fun areItemsTheSame(newItem: GeneraEntity): Boolean =
        newItem is SearchResponse && this.id == newItem.id

    override fun areContentsTheSame(newItem: GeneraEntity): Boolean =
        newItem is SearchResponse && this == newItem

    companion object {
        const val MOVIE = "movie"
        const val PERSON = "person"
    }
}
