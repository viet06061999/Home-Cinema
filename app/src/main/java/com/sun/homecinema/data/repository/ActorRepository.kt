package com.sun.homecinema.data.repository

import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import io.reactivex.Observable

interface ActorRepository {

    fun getMoviesOfActor(actorId: Int): Observable<List<Movie>>

    fun getDetailActor(actorId: Int): Observable<Actor>
}
