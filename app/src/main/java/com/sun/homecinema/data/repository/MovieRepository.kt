package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.*
import io.reactivex.Observable

interface MovieRepository {

    fun getMoviesByType(type: MovieType): Observable<List<Movie>>

    fun getMoviesByGenreId(id: Int): Observable<List<Movie>>

    fun getDetailMovie(movieId: Int): Observable<MovieResponse>

    fun getActors(movieId: Int): Observable<List<Actor>>

    fun getRecommendMovies(movieId: Int): Observable<List<Movie>>

    fun getVideo(movieId: Int): Observable<Video>
}
