package com.sun.homecinema.data.source.remote

import com.sun.homecinema.data.model.*
import com.sun.homecinema.data.source.MovieDataSource
import com.sun.homecinema.data.source.remote.utils.MovieService
import io.reactivex.Observable

class MovieRemoteDataSource(private val movieService: MovieService) :
    MovieDataSource.Remote {

    override fun getPopularMovies(page: Int?): Observable<MovieListResponse> =
        movieService.getPopularMovies(page)

    override fun getTopRateMovies(page: Int?): Observable<MovieListResponse> =
        movieService.getTopRateMovies(page)

    override fun getUpComingMovies(page: Int?): Observable<MovieListResponse> =
        movieService.getUpComingMovies(page)

    override fun getMovieByGenre(genreId: Int, page: Int?): Observable<MovieListResponse> =
        movieService.getMovieByGenre(genreId, page)

    override fun getDetailMovie(movieId: Int): Observable<MovieResponse> =
        movieService.getDetailMovie(movieId)

    override fun getActor(movieId: Int): Observable<ActorResponse> =
        movieService.getActor(movieId)

    override fun getRecommendMovie(movieId: Int): Observable<MovieListResponse> =
        movieService.getRecommendMovie(movieId)

    override fun getVideo(movieId: Int): Observable<VideoResponse> =
        movieService.getVideo(movieId)

    override fun search(param: String): Observable<ListSearch> =
        movieService.search(param)
}
