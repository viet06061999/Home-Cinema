package com.sun.homecinema.data.source.remote.utils

import com.sun.homecinema.data.model.*
import com.sun.homecinema.utils.ApiEndPoint
import com.sun.homecinema.utils.ApiEndPoint.PARAMS_GENRE_ID
import com.sun.homecinema.utils.ApiEndPoint.PARAMS_ID
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(ApiEndPoint.GET_POPULAR_MOVIE)
    fun getPopularMovies(@Query("page") page: Int? = null): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_TOP_RATE_MOVIE)
    fun getTopRateMovies(@Query("page") page: Int? = null): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_UPCOMING_MOVIE)
    fun getUpComingMovies(@Query("page") page: Int? = null): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_MOVIE_BY_GENRE)
    fun getMovieByGenre(
        @Query(PARAMS_GENRE_ID) genreId: Int,
        @Query("page") page: Int? = null
    ): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_DETAIL_MOVIE)
    fun getDetailMovie(@Path(PARAMS_ID) movieId: Int): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_ACTOR_OF_MOVIE)
    fun getActor(@Path(PARAMS_ID) movieId: Int): Observable<ActorResponse>

    @GET(ApiEndPoint.GET_RECOMMEND_MOVIE)
    fun getRecommendMovie(@Path(PARAMS_ID) movieId: Int): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_VIDEO)
    fun getVideo(@Path(PARAMS_ID) movieId: Int): Observable<VideoResponse>

    @GET(ApiEndPoint.SEARCH_MUlTI)
    fun search(@Query("query") query: String): io.reactivex.rxjava3.core.Observable<ListSearch>
}
