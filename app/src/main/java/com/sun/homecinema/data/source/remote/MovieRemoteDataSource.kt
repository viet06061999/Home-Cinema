package com.sun.homecinema.data.source.remote

import com.sun.homecinema.data.model.ActorResponse
import com.sun.homecinema.data.model.MovieListResponse
import com.sun.homecinema.data.model.MovieResponse
import com.sun.homecinema.data.model.VideoResponse
import com.sun.homecinema.data.source.MovieDataSource
import com.sun.homecinema.data.source.remote.utils.MovieService
import io.reactivex.Observable

class MovieRemoteDataSource(private val movieService: MovieService) :
    MovieDataSource.Remote {

    override fun getPopularMovies(): Observable<MovieListResponse> =
        movieService.getPopularMovies()

    override fun getTopRateMovies(): Observable<MovieListResponse> =
        movieService.getTopRateMovies()

    override fun getUpComingMovies(): Observable<MovieListResponse> =
        movieService.getUpComingMovies()

    override fun getMovieByGenre(genreId: Int): Observable<MovieListResponse> =
        movieService.getMovieByGenre(genreId)

    override fun getDetailMovie(movieId: Int): Observable<MovieResponse> =
        movieService.getDetailMovie(movieId)

    override fun getActor(movieId: Int): Observable<ActorResponse> =
        movieService.getActor(movieId)

    override fun getRecommendMovie(movieId: Int): Observable<MovieListResponse> =
        movieService.getRecommendMovie(movieId)

    override fun getVideo(movieId: Int): Observable<VideoResponse> =
        movieService.getVideo(movieId)
}
