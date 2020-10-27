package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.*
import com.sun.homecinema.data.source.MovieDataSource
import io.reactivex.Observable

class MovieRepositoryImpl(private val remote: MovieDataSource.Remote) :
    MovieRepository {

    override fun getMoviesByType(type: MovieType): Observable<List<Movie>> =
        when (type) {
            MovieType.Popular -> remote.getPopularMovies()
            MovieType.TopRate -> remote.getTopRateMovies()
            MovieType.Upcoming -> remote.getUpComingMovies()
        }.map { getMovies(it) }

    override fun getMoviesByGenreId(id: Int): Observable<List<Movie>> =
        remote.getMovieByGenre(id).map { getMovies(it) }

    override fun getDetailMovie(movieId: Int): Observable<MovieResponse> =
        remote.getDetailMovie(movieId)

    override fun getActors(movieId: Int): Observable<List<Actor>> =
        remote.getActor(movieId).map { it.cast }

    override fun getRecommendMovies(movieId: Int): Observable<List<Movie>> =
        remote.getRecommendMovie(movieId).map { getMovies(it) }

    override fun getVideo(movieId: Int): Observable<Video> =
        remote.getVideo(movieId).map { getTrailer(it.results) }

    private fun getTrailer(videos: List<Video?>): Video =
        videos.first {
            it?.type == TYPE_TRAILER
        } ?: Video()

    private fun getMovies(moviesResponse: MovieListResponse): List<Movie> =
        moviesResponse.movies.mapNotNull {
            try {
                Movie(it)
            } catch (exception: IllegalArgumentException) {
                null
            }
        }

    companion object {
        const val TYPE_TRAILER = "Trailer"
    }
}
