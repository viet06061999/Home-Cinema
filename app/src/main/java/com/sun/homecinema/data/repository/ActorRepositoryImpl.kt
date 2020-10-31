package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.source.ActorDataSource
import io.reactivex.Observable

class ActorRepositoryImpl(private val remote: ActorDataSource.Remote) :
    ActorRepository {

    override fun getMoviesOfActor(actorId: Int): Observable<List<Movie>> =
        remote.getMovieOfActor(actorId).map {
            it.movies.mapNotNull { response ->
                try {
                    Movie(response)
                } catch (exception: IllegalArgumentException) {
                    null
                }
            }
        }

    override fun getDetailActor(actorId: Int): Observable<Actor> =
        remote.getDetailActor(actorId)
}
