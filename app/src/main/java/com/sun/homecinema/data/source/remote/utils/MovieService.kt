package com.sun.homecinema.data.source.remote.utils

import com.sun.homecinema.data.model.ActorResponse
import com.sun.homecinema.data.model.MovieListResponse
import com.sun.homecinema.data.model.MovieResponse
import com.sun.homecinema.data.model.VideoResponse
import com.sun.homecinema.utils.ApiEndPoint
import com.sun.homecinema.utils.ApiEndPoint.PARAMS_ID
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET(ApiEndPoint.GET_POPULAR_MOVIE)
    fun getPopularMovies(): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_TOP_RATE_MOVIE)
    fun getTopRateMovies(): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_UPCOMING_MOVIE)
    fun getUpComingMovies(): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_MOVIE_BY_GENRE)
    fun getMovieByGenre(@Path(PARAMS_ID) genreId: Int): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_DETAIL_MOVIE)
    fun getDetailMovie(@Path(PARAMS_ID) movieId: Int): Observable<MovieResponse>

    @GET(ApiEndPoint.GET_ACTOR_OF_MOVIE)
    fun getActor(@Path(PARAMS_ID) movieId: Int): Observable<ActorResponse>

    @GET(ApiEndPoint.GET_RECOMMEND_MOVIE)
    fun getRecommendMovie(@Path(PARAMS_ID) movieId: Int): Observable<MovieListResponse>

    @GET(ApiEndPoint.GET_VIDEO)
    fun getVideo(@Path(PARAMS_ID) movieId: Int): Observable<VideoResponse>
}
