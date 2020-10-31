package com.sun.homecinema.data.source

import com.sun.homecinema.data.model.*
import io.reactivex.Observable

interface MovieDataSource {

    interface Remote {

        fun getPopularMovies(page: Int? = null): Observable<MovieListResponse>

        fun getTopRateMovies(page: Int? = null): Observable<MovieListResponse>

        fun getUpComingMovies(page: Int? = null): Observable<MovieListResponse>

        fun getMovieByGenre(genreId: Int, page: Int? = null): Observable<MovieListResponse>

        fun getDetailMovie(movieId: Int): Observable<MovieResponse>

        fun getActor(movieId: Int): Observable<ActorResponse>

        fun getRecommendMovie(movieId: Int): Observable<MovieListResponse>

        fun getVideo(movieId: Int): Observable<VideoResponse>

        fun search(param: String): Observable<ListSearch>
    }
}
