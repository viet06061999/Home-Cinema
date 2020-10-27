package com.sun.homecinema.data.model

import com.google.gson.annotations.SerializedName
import com.sun.homecinema.utils.GenreUtil

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var rate: Float,
    @SerializedName("overview")
    var description: String,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("backdrop_path")
    var background: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("runtime")
    var runtime: Int,
    @SerializedName("genre_ids")
    var genreId: List<Int>?,
    @SerializedName("genres")
    var genre: List<GenreResponse>?,
    @SerializedName("production_companies")
    var company: MutableList<CompanyResponse>?
) {

    fun getCompanyPopular(): List<CompanyResponse> {
            val companyDefault = company?.getOrNull(0) ?: CompanyResponse()
            val companies = company?.filter {
                !it.logo.isNullOrEmpty()
            }
            return if (companies.isNullOrEmpty()) listOf(companyDefault) else companies

    }

    fun getGenreDefaultId(): Int =
        genreId?.getOrNull(0) ?: GenreUtil.getIdGenre(ACTION_MOVIE)

    companion object {
        private const val ACTION_MOVIE = "Action"
    }
}
