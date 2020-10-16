package com.sun.homecinema.data.source

import com.sun.homecinema.data.model.ActorResponse
import com.sun.homecinema.data.model.MovieListResponse
import com.sun.homecinema.data.model.MovieResponse
import com.sun.homecinema.data.model.VideoResponse
import io.reactivex.Observable

interface MovieDataSource {

    interface Remote {

        fun getPopularMovies(): Observable<MovieListResponse>

        fun getTopRateMovies(): Observable<MovieListResponse>

        fun getUpComingMovies(): Observable<MovieListResponse>

        fun getMovieByGenre(genreId: Int): Observable<MovieListResponse>

        fun getDetailMovie(movieId: Int): Observable<MovieResponse>

        fun getActor(movieId: Int): Observable<ActorResponse>

        fun getRecommendMovie(movieId: Int): Observable<MovieListResponse>

        fun getVideo(movieId: Int): Observable<VideoResponse>
    }
}
