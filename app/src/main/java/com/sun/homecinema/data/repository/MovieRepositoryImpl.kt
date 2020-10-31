package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.*
import com.sun.homecinema.data.source.MovieDataSource
import io.reactivex.Observable

class MovieRepositoryImpl(private val remote: MovieDataSource.Remote) :
    MovieRepository {

    override fun getMoviesByType(type: MovieType, page: Int?): Observable<List<Movie>> =
        when (type) {
            MovieType.Popular -> remote.getPopularMovies(page)
            MovieType.TopRate -> remote.getTopRateMovies(page)
            MovieType.Upcoming -> remote.getUpComingMovies(page)
        }.map { getMovies(it) }

    override fun getMoviesByGenreId(id: Int, page: Int?): Observable<List<Movie>> =
        remote.getMovieByGenre(id, page).map {
            getMovies(it).filter { movie ->
                !movie.poster.isNullOrEmpty()
            }
        }

    override fun getDetailMovie(movieId: Int): Observable<MovieResponse> =
        remote.getDetailMovie(movieId)

    override fun getActors(movieId: Int): Observable<List<Actor>> =
        remote.getActor(movieId).map {
            it.cast.filter { actor ->
                !actor.avatar.isNullOrEmpty()
            }
        }

    override fun getRecommendMovies(movieId: Int): Observable<List<Movie>> =
        remote.getRecommendMovie(movieId).map {
            getMovies(it).filter { movie ->
                !movie.poster.isNullOrEmpty()
            }
        }

    override fun getVideo(movieId: Int): Observable<Video> =
        remote.getVideo(movieId).map { getTrailer(it.results) }

    override fun search(param: String): Observable<List<SearchResponse>> =
        remote.search(param).map {
            it.response.filter { item ->
                item.mediaType == SearchResponse.MOVIE || item.mediaType == SearchResponse.PERSON
            }
        }

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
